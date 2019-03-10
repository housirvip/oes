package vip.housir.store.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.store.entity.Product;

/**
 * @author housirvip
 */
public interface ProductService {

    /**
     * 获取 Product 记录
     *
     * @param id  Integer
     * @return Product
     */
    Product oneById(Integer id);

    /**
     * 根据参数查询，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Product> pageByParam(PageDto pageDto);

    /**
     * 更新或创建 Product
     *
     * @param product Product
     */
    Integer createOrUpdate(Product product);
}
