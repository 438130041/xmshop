package com.ly.shopping.service.impl;

import com.alipay.api.domain.UserDetails;
import com.ly.shopping.dao.UserMapper;
import com.ly.shopping.pojo.Alike;
import com.ly.shopping.pojo.User;
import com.ly.shopping.pojo.UserExample;
import com.ly.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void delete(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateByPrimaryKey(User record) {

        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User get(int id) {

        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> list() {
        UserExample example = new UserExample();
        // example.setOrderByClause("id desc");
        return userMapper.selectByExample(example);
    }

    //模糊查询
    @Override
    public List<User> alikeList(String find_value, String input_value) {
        Alike alike = new Alike(find_value, input_value);
        System.out.println("*" + find_value);
        return userMapper.selectByExampleAlike(alike);
    }

    @Override
    public Integer getTotal(User user) {
        return userMapper.selectTotal(user);
    }

    @Override
    public User get(String name, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<User> result = userMapper.selectByExample(example);
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    @Override
    public boolean isExist(String name) {
        UserExample example = new UserExample();
        example.or().andNameEqualTo(name);
        List<User> result = userMapper.selectByExample(example);
        if (!result.isEmpty())
            return true;
        return false;
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public int find(String name, String password) {
        return userMapper.find(name, password);
    }

    @Override
    public User queryxmuser(String name) {

        return userMapper.queryxmuser(name);
    }

    @Override
    public void pencer_update(User user) {
        userMapper.pencer_update(user);
    }
       @Override
    public User get1(String name) {
        User  u= userMapper.selectUserByName(name);

        return  u;
    }

}