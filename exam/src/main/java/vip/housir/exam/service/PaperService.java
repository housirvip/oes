package vip.housir.exam.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Paper;
import vip.housir.exam.entity.Question;
import vip.housir.exam.entity.Section;

/**
 * @author housirvip
 */
public interface PaperService {
    /**
     * 根据 Paper 主键，生成一张完整的试卷
     *
     * @param id Integer
     * @return Paper
     */
    Paper render(Integer id);

    /**
     * 根据参数查询，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Paper> pageByParam(PageDto pageDto);

    /**
     * 根据主键查询 Question
     *
     * @param id Integer
     * @return Question
     */
    Question question(Integer id);

    /**
     * 根据主键查询 Section
     *
     * @param id Integer
     * @return Section
     */
    Section section(Integer id);

    /**
     * 根据主键查询 Paper
     *
     * @param id Integer
     * @return Paper
     */
    Paper paper(Integer id);

    /**
     * 更新 Question
     *
     * @param record Question
     * @return Boolean
     */
    Integer createOrUpdate(Question record);

    /**
     * 更新 Question
     *
     * @param record Section
     * @return Boolean
     */
    Integer createOrUpdate(Section record);

    /**
     * 更新 Question
     *
     * @param record Paper
     * @return Boolean
     */
    Integer createOrUpdate(Paper record);
}
