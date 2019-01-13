package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.entity.Question;
import vip.housir.exam.entity.Section;
import vip.housir.exam.mapper.ExamMapper;
import vip.housir.exam.mapper.PaperMapper;
import vip.housir.exam.mapper.QuestionMapper;
import vip.housir.exam.mapper.SectionMapper;
import vip.housir.exam.mqhandler.ExamOutput;
import vip.housir.exam.service.ExamService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author housirvip
 */
@Service
@EnableBinding(ExamOutput.class)
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;
    private final PaperMapper paperMapper;
    private final SectionMapper sectionMapper;
    private final QuestionMapper questionMapper;

    private final ExamOutput examOutput;

    @Value("${exam.score-async}")
    private Boolean scoreAsync;

    @Override
    public Exam one(Integer id, Integer uid) {

        Exam exam = examMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(exam, ErrorMessage.EXAM_NOT_FOUND);

        Optional.ofNullable(uid)
                .ifPresent(u -> Preconditions.checkArgument(u.equals(exam.getUid()), ErrorMessage.EXAM_PERMISSION_DENY));

        return exam;
    }

    @Override
    public Page<Exam> pageByParam(PageDto pageDto) {

        Page<Exam> examPage = examMapper.listByParam(pageDto.putParam().getParamAsMap());

        examPage.forEach(item -> {
            item.setSectionScore(null);
            item.setUserAnswer(null);
        });

        return examPage;
    }

    @Override
    public Integer submit(Exam exam) {

        exam.setCreateTime(new Date());
        examMapper.insertSelective(exam);

        if (scoreAsync) {
            //异步后端打分
            examOutput.score().send(MessageBuilder.withPayload(exam.getId()).build());
        }

        return exam.getId();
    }

    @Override
    public void score(Integer id) {

        Exam exam = examMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(exam, ErrorMessage.EXAM_NOT_FOUND);

        Paper paper = paperMapper.selectByPrimaryKey(exam.getPid());
        Preconditions.checkNotNull(paper, ErrorMessage.PAPER_NOT_FOUND);

        Map<String, Double> sectionScore = Maps.newHashMap();
        List<Section> sectionList = sectionMapper.listByPid(paper.getId());
        sectionList.forEach(section -> {

            //thisScore 为此 section 得分
            AtomicReference<Float> thisScore = new AtomicReference<>(0f);

            List<Question> questionList = questionMapper.listBySid(section.getId());
            float everyScore = section.getTotalScore() / questionList.size();
            questionList.forEach(question -> {

                String answer = exam.getUserAnswer().get(question.getId().toString());
                if (answer == null || question.getAnswer() == null) {
                    return;
                }
                if (answer.equals(question.getAnswer())) {
                    //答对加分
                    thisScore.updateAndGet(v -> v + everyScore);
                } else if (section.getDeduct()) {
                    //答错扣分，如果 section 开启了 deduct
                    thisScore.updateAndGet(v -> v - everyScore);
                }
            });
            if (thisScore.get() < 0) {
                //不允许负分
                thisScore.set(0f);
            }
            BigDecimal finalScore = new BigDecimal(thisScore.get()).setScale(2, BigDecimal.ROUND_HALF_UP);
            thisScore.set(finalScore.floatValue());
            sectionScore.put(section.getId().toString(), finalScore.doubleValue());
        });
        sectionScore.forEach((k, v) -> exam.setScore(exam.getScore() + v.floatValue()));
        exam.setSectionScore(sectionScore);
        exam.setUserAnswer(null);

        examMapper.updateByPrimaryKeySelective(exam);
    }
}
