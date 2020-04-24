

package com.ly.shopping.service;

import com.ly.shopping.pojo.Review;

import java.util.List;

public interface ReviewService {
     

    void add(Review c);

    void delete(int id);
    void update(Review c);
    Review get(int id);
    List list(int pid);
    List admin_all_list();

    int getCount(int pid);

    /**
     * 根据product_id来返回当前产品下的所有评论
     *
     * @param product_id
     * @return
     */
    List<Review> listByProductId(Integer product_id);

}
