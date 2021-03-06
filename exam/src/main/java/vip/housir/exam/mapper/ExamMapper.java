package vip.housir.exam.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import vip.housir.exam.entity.Exam;

import java.util.Map;

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

    /**
     * 根据参数查询，支持分页
     *
     * @param param Map<String,Object>
     * @return Page
     */
    Page<Exam> listByParam(Map<String, Object> param);

    /**
     * 根据 pids 和 uid 查询 exam 中考试记录数量
     *
     * @param param Map<String, Object>
     * @return Map
     */
    @MapKey("pid")
    Map<Integer, Map<String, Long>> countTimesByPids(Map<String, Object> param);
}