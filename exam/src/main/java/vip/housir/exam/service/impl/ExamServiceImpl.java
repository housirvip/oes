package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.mapper.ExamMapper;
import vip.housir.exam.mapper.PaperMapper;
import vip.housir.exam.service.ExamService;

import java.util.Date;
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
    private final PaperMapper paperMapper;

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
    public Boolean submit(Exam exam) {

        exam.setCreateTime(new Date());

        //TODO 后端打分

        return examMapper.insertSelective(exam) == 1;
    }

    @Override
    public Boolean score(Integer id) {

        Exam exam = examMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(exam, ErrorMessage.EXAM_NOT_FOUND);

        Paper paper = paperMapper.selectByPrimaryKey(exam.getPid());
        Preconditions.checkNotNull(paper, ErrorMessage.PAPER_NOT_FOUND);

        Map<String, Double> sectionScore = Maps.newHashMap();
        paper.getSections().forEach(section -> {

            //thisScore 为此 section 得分
            AtomicReference<Float> thisScore = new AtomicReference<>(0f);
            float everyScore = section.getTotalScore() / section.getQids().size();
            section.getQuestions().forEach(question -> {
                if (question.getAnswer() == null) {
                    return;
                }
                if (question.getAnswer().equals(exam.getUserAnswer().get(question.getId().toString()))) {
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
            sectionScore.put(section.getId().toString(), thisScore.get().doubleValue());
        });
        sectionScore.forEach((k, v) -> exam.setScore(exam.getScore() + v.floatValue()));
        exam.setSectionScore(sectionScore);
        exam.setUserAnswer(null);

        return examMapper.updateByPrimaryKeySelective(exam) > 0;
    }
}
