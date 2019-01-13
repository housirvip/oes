package vip.housir.exam.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.exam.entity.Exam;

/**
 * @author housirvip
 */
public interface ExamService {
    /**
     * 根据 Exam 主键，获取考试详情
     *
     * @param id  Integer
     * @param uid Integer
     * @return Exam
     */
    Exam one(Integer id, Integer uid);

    /**
     * 根据参数查询，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Exam> pageByParam(PageDto pageDto);

    /**
     * 插入考试记录，返回主键 id
     *
     * @param exam Exam
     * @return Integer
     */
    Integer submit(Exam exam);

    /**
     * 将存入数据库中的考试记录打分
     *
     * @param id Integer
     */
    void score(Integer id);
}
