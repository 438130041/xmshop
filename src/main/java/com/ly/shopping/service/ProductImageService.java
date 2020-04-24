/**
* 模仿天猫整站ssm 教程 为how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	

package com.ly.shopping.service;
import com.ly.shopping.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    void add(ProductImage pi);
    void delete(int id);
    void update(ProductImage pi);
    ProductImage get(int id);

    //前台
    /*查询商品图片*/
    List<String> queryimg( int pid);

    /*查询默认商品图片*/
    String queryMoimg(int pid);

}
