package vip.housir.exam.service;

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
}
