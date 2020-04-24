package com.ly.shopping.dao;

import com.ly.shopping.pojo.Alike;
import com.ly.shopping.pojo.User;
import com.ly.shopping.pojo.UserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //模糊查询
    List<User> selectByExampleAlike (Alike alike);

    Integer selectTotal(@Param("user") User user);

    int find(@Param("name") String name, @Param("password") String password);


    User queryxmuser(@Param("name") String name);

     void pencer_update(User user);

    User selectUserByName(String name);
}