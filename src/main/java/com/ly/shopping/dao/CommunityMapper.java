package com.ly.shopping.dao;

import com.ly.shopping.pojo.Community;
import com.ly.shopping.pojo.CommunityExample;
import java.util.List;

public interface CommunityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Community record);

    int insertSelective(Community record);

    List<Community> selectByExampleWithBLOBs(CommunityExample example);

    List<Community> selectByExample(CommunityExample example);

    Community selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKeyWithBLOBs(Community record);

    int updateByPrimaryKey(Community record);
}