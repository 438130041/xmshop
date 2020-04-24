package com.ly.shopping.service.impl;

import com.ly.shopping.dao.ColorMapper;
import com.ly.shopping.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorMapper colorMapper;
    @Override
    public List<String> querycolor(int GoodsID) {

        return colorMapper.querycolor(GoodsID);
    }
}
