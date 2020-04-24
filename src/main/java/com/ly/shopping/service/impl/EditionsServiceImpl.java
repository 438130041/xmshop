package com.ly.shopping.service.impl;

import com.ly.shopping.dao.EditionsMapper;
import com.ly.shopping.service.EditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EditionsServiceImpl implements EditionsService {
    @Autowired
    private EditionsMapper editionsMapper;
   @Override
    public List<String> queryedition(int GoodsID) {

        return editionsMapper.queryedition(GoodsID);
    }

}
