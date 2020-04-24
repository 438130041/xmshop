package com.ly.shopping.service.impl;

import com.ly.shopping.dao.CarouselMapper;
import com.ly.shopping.pojo.Carousel;
import com.ly.shopping.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;
    @Override
    public List<Map<String, Object>> carousel_show() {
        return carouselMapper.carousel_show();
    }

    @Override
    public int carousel_update(Carousel carousel) {
        return carouselMapper.carousel_update(carousel);
    }

    @Override
    public void carousel_del(int id) {
        carouselMapper.carousel_del(id);

    }

    @Override
    public Carousel carousel_selectID(int id) {
        return carouselMapper.carousel_selectID(id);
    }

    @Override
    public void carousel_updateFlag(int id, String flag) {
        carouselMapper.carousel_updateFlag(id,flag);

    }

    @Override
    public List<Carousel> querycarousel() {
        return carouselMapper.querycarousel();
    }
}
