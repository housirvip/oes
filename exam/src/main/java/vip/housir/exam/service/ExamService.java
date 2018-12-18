package vip.housir.exam.service;

import com.github.pagehelper.Page;
import vip.housir.exam.entity.Exam;

import java.util.Map;

/**
 * @author housirvip
 */
public interface ExamService {
    /**
     * 根据 Exam 主键，获取考试详情
     *
     * @param id Integer
     * @return Exam
     */
    Exam one(Integer id);

    /**
     * 根据参数查询，支持分页
     * pageSize,PageNum
     *
     * @param param Map<String, Object>
     * @return Page
     */
    Page<Exam> pageByParam(Map<String, Object> param);

    /**
     * 插入考试记录
     *
     * @param exam Exam
     * @return Boolean
     */
    Integer submit(Exam exam);
}
