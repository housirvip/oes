package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vip.housir.base.request.PageRequest;
import vip.housir.base.response.ErrorMessage;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.mapper.ExamMapper;
import vip.housir.exam.service.ExamService;

import java.util.Date;

/**
 * @author housirvip
 */
@Slf4j
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
    public Page<Exam> pageByParam(PageRequest pageRequest) {

        Page<Exam> examPage = examMapper.listByParam(pageRequest.toMap());

        examPage.forEach(item -> {
            item.setSectionScore(null);
            item.setUserAnswer(null);
        });

        return examPage;
    }

    @Override
    public Integer submit(Exam exam) {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        exam.setCreateTime(new Date());
        exam.setUid(uid);

        return examMapper.insertSelective(exam);
    }
}
