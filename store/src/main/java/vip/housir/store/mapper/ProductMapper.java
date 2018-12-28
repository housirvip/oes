package vip.housir.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.housir.store.entity.Product;

/**
 * @author housirvip
 */
@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}