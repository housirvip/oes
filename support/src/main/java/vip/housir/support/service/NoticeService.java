package vip.housir.support.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.support.entity.Notice;

/**
 * @author housirvip
 */
public interface NoticeService {
    /**
     * 获取 Notice 记录
     *
     * @param id Integer
     * @return Ticket
     */
    Notice oneById(Integer id);

    /**
     * 根据参数查询，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Notice> pageByParam(PageDto pageDto);

    /**
     * 创建或更新，返回主键 id
     *
     * @param record Notice
     * @return Boolean
     */
    Integer createOrUpdate(Notice record);
}
