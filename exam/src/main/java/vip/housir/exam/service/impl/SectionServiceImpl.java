package vip.housir.exam.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Section;
import vip.housir.exam.mapper.SectionMapper;
import vip.housir.exam.service.SectionService;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionMapper sectionMapper;

    @Override
    public Page<Section> pageByParam(PageDto pageDto) {

        return sectionMapper.listByParam(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Section oneById(Integer id) {

        return sectionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer createOrUpdate(Section record) {

        if (record.getId() == null) {
            return sectionMapper.insertSelective(record);
        }

        return sectionMapper.updateByPrimaryKeySelective(record);
    }
}
