package vip.housir.support.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.dto.PageDto;
import vip.housir.support.entity.Notice;
import vip.housir.support.mapper.NoticeMapper;
import vip.housir.support.service.NoticeService;

import java.util.Date;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Override
    public Notice oneById(Integer id) {

        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Notice> pageByParam(PageDto pageDto) {

        return noticeMapper.listByParam(pageDto.getParamAsMap());
    }

    @Override
    public Integer createOrUpdate(Notice notice) {

        if (notice.getId() == null) {
            notice.setCreateTime(new Date());
            noticeMapper.insertSelective(notice);
        } else {
            noticeMapper.updateByPrimaryKeySelective(notice);
        }

        return notice.getId();
    }
}
