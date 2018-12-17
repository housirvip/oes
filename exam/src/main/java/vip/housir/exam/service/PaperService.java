package vip.housir.exam.service;

import com.github.pagehelper.Page;
import vip.housir.exam.entity.Paper;

import java.util.Map;

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
     * pageSize,PageNum
     *
     * @param param Map<String, Object>
     * @return Page<Paper>
     */
    Page<Paper> pageByParam(Map<String, Object> param);
}
