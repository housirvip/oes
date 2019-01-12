package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Question;
import vip.housir.exam.mapper.QuestionMapper;
import vip.housir.exam.service.QuestionService;

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
    public Integer createOrUpdate(Question record) {

        if (record.getId() == null) {
            return questionMapper.insertSelective(record);
        }

        return questionMapper.updateByPrimaryKeySelective(record);
    }
}
