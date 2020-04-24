package com.ly.shopping.service.impl;

import com.ly.shopping.dao.StatMapper;
import com.ly.shopping.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatServiceImpl implements StatService {
    @Autowired
    StatMapper statMapper;
    @Override
    public List<Integer> queryAllStar() {
        return statMapper.queryAllStar();
    }
}
