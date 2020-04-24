package com.ly.shopping.service.impl;

import com.ly.shopping.dao.ProductMapper;
import com.ly.shopping.pojo.Category;
import com.ly.shopping.pojo.Product;
import com.ly.shopping.pojo.ProductExample;
import com.ly.shopping.service.CategoryService;
import com.ly.shopping.service.ProductService;
import com.ly.shopping.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReviewService reviewService;
    @Override
    public List<Product> findAllProduct() {
            ProductExample productExample = new ProductExample();
            productExample.createCriteria();
           // productExample.setOrderByClause("id desc");
            List<Product> result = productMapper.selectByExample(productExample);
            return result;
        }
    @Override
    public  void insert(Product product){
        productMapper.insert(product);
    }
    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product p) {
        productMapper.updateByPrimaryKeySelective(p);
    }
    @Override
    public Product get(int id) {
        Product p = productMapper.selectByPrimaryKey(id);
        setCategory(p);

        return p;
    }
    public void setCategory(List<Product> ps) {
        for (Product p : ps)
            setCategory(p);
    }
    public void setCategory(Product p) {
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }
    @Override
    public Integer getTotal(Product product,Byte[] product_isEnabled_array) {
        return productMapper.selectTotal(product,product_isEnabled_array);
    }
    @Override
    public void fill(Category c) {
        List<Product> ps = list(c.getId());
        c.setProducts(ps);
    }
    @Override
    public List list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
      //  example.setOrderByClause("id desc");
        List result = productMapper.selectByExample(example);
        setCategory(result);
        return result;
    }

    @Override
    public void setSaleAndReviewNumber(Product p) {
      //  int saleCount = orderItemService.getSaleCount(p.getId());
       // p.setSaleCount(saleCount);
        int reviewCount = reviewService.getCount(p.getId());
        p.setReviewCount(reviewCount);
    }
    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {
        for (Product p : ps) {
            setSaleAndReviewNumber(p);
        }
    }
    @Override
    public String querylx( int id){
        return productMapper.querylx(id);
    }
    @Override
    public Product queryGoods(int id){
        return productMapper.queryGoods(id);
    }

    @Override
    public void setReviewCount(Product product) {
        int reviewCount = reviewService.getCount(product.getId());
        product.setReviewCount(reviewCount);
    }
    @Override
    public Product findById(Integer productId) {
        return productMapper.findByProductId(productId);

    }


}
