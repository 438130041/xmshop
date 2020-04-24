package com.ly.shopping.service;

import com.ly.shopping.pojo.User;

import java.util.List;

public interface UserService {

    void delete(int id);

    void update(User user);

    int updateByPrimaryKey(User record);
    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    User get(int id);

    List<User> list();

    List<User> alikeList(String find_value, String input_value);

    Integer getTotal(User user);

    User get1(String name);

    /**
     * 根据用户名和密码来查询用户
     *
     * @param name
     * @param password
     * @return
     */
    User get(String name, String password);

    /**
     * 根据用户名判断用户是否存在
     *
     * @param name
     * @return
     */
    boolean isExist(String name);

    /**
     * 增加一条用户数据
     *
     * @param user
     */
    void add(User user);

    int find( String name, String password);

    User queryxmuser( String name);

    void pencer_update(User user);

}
