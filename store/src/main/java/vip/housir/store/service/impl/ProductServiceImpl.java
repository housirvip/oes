package vip.housir.store.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.dto.PageDto;
import vip.housir.store.entity.Product;
import vip.housir.store.mapper.ProductMapper;
import vip.housir.store.service.ProductService;

import java.util.Date;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public Product oneById(Integer id) {

        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Product> pageByParam(PageDto pageDto) {

        return productMapper.listByParam(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Integer createOrUpdate(Product product) {

        if (product.getId() == null) {
            product.setCreateTime(new Date());
            productMapper.insertSelective(product);
        } else {
            productMapper.updateByPrimaryKeySelective(product);
        }

        return product.getId();
    }
}
