package vip.housir.exam.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import vip.housir.exam.entity.Section;

import java.util.List;
import java.util.Map;

/**
 * @author housirvip
 */
@Mapper
public interface SectionMapper {
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
     * @param record Section
     * @return int
     */
    int insert(Section record);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null 则不赋值，取数据库默认值
     *
     * @param record Section
     * @return int
     */
    int insertSelective(Section record);

    /**
     * 根据主键查询记录，返回一条记录或者 null
     *
     * @param id Integer
     * @return Section
     */
    Section selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则不赋值
     *
     * @param record Section
     * @return int
     */
    int updateByPrimaryKeySelective(Section record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record Section
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(Section record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record Section
     * @return int
     */
    int updateByPrimaryKey(Section record);

    /**
     * 根据参数查询，支持分页
     *
     * @param param Map<String,Object>
     * @return Page
     */
    Page<Section> listByParam(Map<String, Object> param);

    /**
     * 获取 id 在 ids 列表中的 Section 列表
     * Map 的 key 为 id，value 为 Section
     *
     * @param ids List<Integer>
     * @return Map
     */
    @MapKey("id")
    Map<Integer, Section> listInIds(List<Integer> ids);
}