package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.housir.base.constant.Constant;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.entity.Question;
import vip.housir.exam.entity.Section;
import vip.housir.exam.mapper.ExamMapper;
import vip.housir.exam.mapper.QuestionMapper;
import vip.housir.exam.mapper.SectionMapper;
import vip.housir.exam.mq.ExamSender;
import vip.housir.exam.service.ExamService;
import vip.housir.exam.utils.CacheUtils;

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
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;
    private final SectionMapper sectionMapper;
    private final QuestionMapper questionMapper;

    private final CacheUtils cacheUtils;

    private final ExamSender examSender;

    @Value("${exam.score-async}")
    private Boolean scoreAsync;

    @Value("${exam.time-limit}")
    private Integer timeLimit;

    @Override
    public Exam oneById(Integer id, Integer uid) {

        Exam exam = examMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(exam, ErrorMessage.EXAM_NOT_FOUND);
        Preconditions.checkArgument(uid == null || uid.equals(exam.getUid()), ErrorMessage.EXAM_PERMISSION_DENY);

        return exam;
    }

    @Override
    public Page<Exam> pageByParam(PageDto pageDto) {

        return examMapper.listByParam(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Integer submit(Exam exam) {

        //次数上限验证
        Map<Integer, Map<String, Long>> countResult = examMapper.countTimesByPids(
                ImmutableMap.of(Constant.PIDS, ImmutableList.of(exam.getPid()), Constant.UID, exam.getUid()));
        Optional.ofNullable(countResult.get(exam.getPid()))
                .map(map -> map.get(Constant.TIMES))
                .ifPresent(times -> Preconditions.checkArgument(times < timeLimit, ErrorMessage.PAPER_TIMES_LIMIT));

        examSender.sendExam(exam);

        return exam.getId();
    }

    @Override
    public void saveAndScore(Exam exam) {

        Paper paper = cacheUtils.getPaper(exam.getPid());
        Preconditions.checkNotNull(paper, ErrorMessage.PAPER_NOT_FOUND);

        //异步后端打分
        if (scoreAsync) {
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
        }

        exam.setName(paper.getName());
        exam.setCreateTime(new Date());
        examMapper.insertSelective(exam);
    }
}
