package vip.housir.store.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import vip.housir.store.entity.Product;

import java.util.Map;

/**
 * @author housirvip
 */
@Mapper
public interface ProductMapper {
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
     * @param record Product
     * @return int
     */
    int insert(Product record);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null 则不赋值，取数据库默认值
     *
     * @param record Product
     * @return int
     */
    int insertSelective(Product record);

    /**
     * 根据主键查询记录，返回一条记录或者 null
     *
     * @param id Integer
     * @return Product
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则不赋值
     *
     * @param record Product
     * @return int
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record Product
     * @return int
     */
    int updateByPrimaryKey(Product record);

    /**
     * 根据参数查询，支持分页
     *
     * @param param Map<String,Object>
     * @return Page
     */
    Page<Product> listByParam(Map<String, Object> param);
}