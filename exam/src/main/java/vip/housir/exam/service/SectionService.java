package vip.housir.exam.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Section;

/**
 * @author housirvip
 */
public interface SectionService {
    /**
     * 根据参数查询 Section，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Section> pageByParam(PageDto pageDto);

    /**
     * 根据主键查询 Section
     *
     * @param id Integer
     * @return Section
     */
    Section oneById(Integer id);

    /**
     * 创建或更新，返回主键 id
     *
     * @param record Section
     * @return Boolean
     */
    Integer createOrUpdate(Section record);
}
