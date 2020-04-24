package com.ly.shopping.dao;

import com.ly.shopping.pojo.Carousel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


public interface CarouselMapper {

    /*查询轮播信息*/
    public List<Map<String,Object>> carousel_show();


    /*修改轮播信息*/
    public int carousel_update(Carousel carousel);

    /*删除轮播信息*/
    public void carousel_del(int id);

    /*通过ID获取轮播信息  在修改页面获取默认值*/
    public Carousel carousel_selectID(int id);

    //是否显示
    public void carousel_updateFlag(@Param("id") int id, @Param("flag") String flag);

    /* 前台轮播展示 */
    List<Carousel> querycarousel();


}
