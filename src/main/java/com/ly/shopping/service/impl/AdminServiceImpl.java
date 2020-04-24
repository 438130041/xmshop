package com.ly.shopping.service.impl;

import com.ly.shopping.dao.AdminMapper;
import com.ly.shopping.pojo.Admin;
import com.ly.shopping.pojo.AdminExample;
import com.ly.shopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin get(String name, String password) {
        AdminExample example =new AdminExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<Admin> result= adminMapper.selectByExample(example);
        if(result.isEmpty())
            return null;
        return result.get(0);
    }
}
