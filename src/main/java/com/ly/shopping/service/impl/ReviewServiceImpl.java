

package com.ly.shopping.service.impl;

import com.ly.shopping.dao.ReviewMapper;
import com.ly.shopping.pojo.Review;
import com.ly.shopping.pojo.ReviewExample;
import com.ly.shopping.pojo.User;
import com.ly.shopping.service.ReviewService;
import com.ly.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    //增加一个评论
    @Override
    public void add(Review c) {
        reviewMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review c) {
        reviewMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    public List<Review> list(int pid){
        ReviewExample example =new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");

        List<Review> result =reviewMapper.selectByExample(example);
        setUser(result);
        return result;
    }


    public List<Review> admin_all_list(){



        //建立一个 查询对象
        ReviewExample example =new ReviewExample();
        example.setOrderByClause("id desc");


        //映射的数据库  理解错Sql语句就行
        List<Review> result =reviewMapper.selectByExample(example);


        int i=0;
        for (Review review : result) {

            System.out.println(result.get(i).getContent());
            i=i+1;
        }


        return result;
    }


    public void setUser(List<Review> reviews){
        for (Review review : reviews) {
            setUser(review);
        }
    }



    private void setUser(Review review) {
        int uid = review.getUid();
        User user =userService.get(uid);
        review.setUser(user);
    }


    //得到评论总数
    @Override
    public int getCount(int pid){
    return list(pid).size();
    }

    @Override
    public List<Review> listByProductId(Integer product_id) {
        ReviewExample example = new ReviewExample();
        example.or().andPidEqualTo(product_id);
       // example.setOrderByClause("id desc");
        List<Review> reviews = reviewMapper.selectByExample(example);
        setUser(reviews);
        return reviews;
    }
}
