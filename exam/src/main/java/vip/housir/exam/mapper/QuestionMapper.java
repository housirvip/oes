package vip.housir.exam.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import vip.housir.exam.entity.Question;

import java.util.List;
import java.util.Map;

/**
 * @author housirvip
 */
@Mapper
public interface QuestionMapper {
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
     * @param record Question
     * @return int
     */
    int insert(Question record);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null 则不赋值，取数据库默认值
     *
     * @param record Question
     * @return int
     */
    int insertSelective(Question record);

    /**
     * 根据主键查询记录，返回一条记录或者 null
     *
     * @param id Integer
     * @return Question
     */
    Question selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则不赋值
     *
     * @param record Question
     * @return int
     */
    int updateByPrimaryKeySelective(Question record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record Question
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(Question record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record Question
     * @return int
     */
    int updateByPrimaryKey(Question record);

    /**
     * 根据 sid 查询记录
     *
     * @param sid Integer
     * @return List
     */
    List<Question> listBySid(Integer sid);

    /**
     * 根据参数查询，支持分页
     *
     * @param param Map<String,Object>
     * @return Page
     */
    Page<Question> listByParam(Map<String, Object> param);

    /**
     * 获取 id 在 ids 列表中的 Question 列表
     * Map 的 key 为 id，value 为 Section
     *
     * @param ids List<Integer>
     * @return Map
     */
    @MapKey("id")
    Map<Integer, Question> listInIds(List<Integer> ids);
}