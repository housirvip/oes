package vip.housir.exam.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Question;

/**
 * @author housirvip
 */
public interface QuestionService {
    /**
     * 根据参数查询 Question，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Question> pageByParam(PageDto pageDto);

    /**
     * 根据主键查询 Question
     *
     * @param id Integer
     * @return Question
     */
    Question oneById(Integer id);

    /**
     * 更新 Question
     *
     * @param record Question
     * @return Boolean
     */
    Integer createOrUpdate(Question record);
}
