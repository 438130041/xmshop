

package com.ly.shopping.service.impl;
import com.ly.shopping.dao.ProductImageMapper;
import com.ly.shopping.pojo.ProductImage;
import com.ly.shopping.pojo.ProductImageExample;
import com.ly.shopping.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageMapper productImageMapper;
    @Override
    public void add(ProductImage pi) {
        productImageMapper.insert(pi);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage pi) {
        productImageMapper.updateByPrimaryKeySelective(pi);

    }
    @Override
    public ProductImage get(int id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<String> queryimg(int pid){

        return productImageMapper.queryimg(pid);
    }
    @Override
    public String queryMoimg(int pid){

        return productImageMapper.queryMoimg(pid);
    }

}

