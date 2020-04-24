package com.ly.shopping.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColorMapper {
     //前台获取颜色
    List<String> querycolor(@Param("GoodsID") int GoodsID);
}
