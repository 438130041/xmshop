package com.ly.shopping.dao;

import com.ly.shopping.pojo.Product;
import com.ly.shopping.pojo.ProductExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    /*查询所有商品*/
    List<Product> findAllProduct();

    Integer selectTotal(@Param("product") Product product,@Param("product_isEnabled_array") Byte[] product_isEnabled_array);

    /*查询商品名字*/
    String querylx(@Param("id") int id);

    /*根据id查商品*/
     Product queryGoods(@Param("id") int id);

   Product findByProductId(@Param("productId") Integer productId);


}