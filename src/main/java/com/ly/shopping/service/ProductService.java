package com.ly.shopping.service;

import com.ly.shopping.pojo.Category;
import com.ly.shopping.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();

    void insert(Product product);

    Product get(int id);

    void delete(int id);

    void update(Product p);

    Product findById(Integer productId);

    List list(int cid);

    Integer getTotal(Product product,Byte[] product_isEnabled_array);

    void fill(Category c);

    void setSaleAndReviewNumber(Product p);

    void setSaleAndReviewNumber(List<Product> ps);

    /*查询商品名字*/
    String querylx( int id);

    Product queryGoods( int id);



    /**
     * 为产品填充ReviewCount字段
     *
     * @param product
     */
    void setReviewCount(Product product);


}
