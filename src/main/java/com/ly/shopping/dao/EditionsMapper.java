package com.ly.shopping.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EditionsMapper {

    List<String> queryedition(@Param("GoodsID") int GoodsID);
}