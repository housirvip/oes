package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.mapper.ExamMapper;
import vip.housir.exam.service.ExamService;

import java.util.Date;
import java.util.Optional;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;

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
}
