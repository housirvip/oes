package vip.housir.exam.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.housir.exam.entity.Exam;

/**
 * @author housirvip
 */
@Mapper
public interface ExamMapper {
    /**
     * 通过主键删除
     *
     * @param id Integer
     * @return int 删除的行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null，则数据库赋值为 null
     *
     * @param record Exam
     * @return int
     */
    int insert(Exam record);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null 则不赋值，取数据库默认值
     *
     * @param record Exam
     * @return int
     */
    int insertSelective(Exam record);

    /**
     * 根据主键查询记录，返回一条记录或者 null
     *
     * @param id Integer
     * @return Exam
     */
    Exam selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则不赋值
     *
     * @param record Exam
     * @return int
     */
    int updateByPrimaryKeySelective(Exam record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record Exam
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(Exam record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record Exam
     * @return int
     */
    int updateByPrimaryKey(Exam record);
}