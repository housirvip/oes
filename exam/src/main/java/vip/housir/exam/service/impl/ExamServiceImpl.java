package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.response.ErrorMessage;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.mapper.ExamMapper;
import vip.housir.exam.service.ExamService;

import java.util.Date;
import java.util.Map;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;

    @Override
    public Exam one(Integer id) {

        Exam exam = examMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(exam, ErrorMessage.EXAM_NOT_FOUND);

        return exam;
    }

    @Override
    public Page<Exam> pageByParam(Map<String, Object> param) {

        return examMapper.listByParam(param);
    }

    @Override
    public Integer submit(Exam exam) {

        //TODO 设置用户
        exam.setUid(1);
        exam.setCreateTime(new Date());

        return examMapper.insertSelective(exam);
    }
}
