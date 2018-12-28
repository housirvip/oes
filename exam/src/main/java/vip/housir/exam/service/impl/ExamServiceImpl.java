package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vip.housir.base.dto.PageDto;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.exam.entity.Exam;
import vip.housir.exam.mapper.ExamMapper;
import vip.housir.exam.service.ExamService;

import java.util.Date;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;

    @Override
    public Exam one(Integer id) {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Exam exam = examMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(exam, ErrorMessage.EXAM_NOT_FOUND);
        Preconditions.checkArgument(exam.getUid().equals(uid), ErrorMessage.EXAM_PERMISSION_DENY);

        return exam;
    }

    @Override
    public Page<Exam> pageByParam(PageDto pageDto) {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Page<Exam> examPage = examMapper.listByParam(pageDto.putUid(uid).putParam().getParamAsMap());

        examPage.forEach(item -> {
            item.setSectionScore(null);
            item.setUserAnswer(null);
        });

        return examPage;
    }

    @Override
    public Boolean submit(Exam exam) {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        exam.setCreateTime(new Date());
        exam.setUid(uid);

        //TODO 后端打分

        return examMapper.insertSelective(exam) == 1;
    }
}
