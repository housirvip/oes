package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Question;
import vip.housir.exam.mapper.QuestionMapper;
import vip.housir.exam.service.QuestionService;

import java.util.Date;

/**
 * @author: housirvip
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;

    @Override
    public Page<Question> pageByParam(PageDto pageDto) {

        return questionMapper.listByParam(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Question oneById(Integer id) {

        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer createOrUpdate(Question question) {

        if (question.getId() == null) {
            question.setCreateTime(new Date());
            questionMapper.insertSelective(question);
        } else {
            questionMapper.updateByPrimaryKeySelective(question);
        }

        return question.getId();
    }
}
