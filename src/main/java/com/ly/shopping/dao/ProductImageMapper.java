package com.ly.shopping.dao;

import com.ly.shopping.pojo.ProductImage;
import com.ly.shopping.pojo.ProductImageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByExample(ProductImageExample example);

    ProductImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);
     //前台
    /*查询商品图片*/
    List<String> queryimg(@Param("pid") int pid);

    /*查询默认商品图片*/
    String queryMoimg(@Param("pid") int pid);

}