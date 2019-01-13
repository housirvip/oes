package vip.housir.exam.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Paper;

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
     * 根据主键查询 Paper
     *
     * @param id Integer
     * @return Paper
     */
    Paper oneById(Integer id);

    /**
     * 创建或更新，返回主键 id
     *
     * @param record Paper
     * @return Boolean
     */
    Integer createOrUpdate(Paper record);
}
