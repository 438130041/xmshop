package com.ly.shopping.service;

import com.ly.shopping.pojo.Product;
import com.ly.shopping.pojo.ShoppingCar;

import java.util.List;

public interface ShoppingCarService {
    //点击增加购物车，商品数量默认为1
    void addShoppingCar(ShoppingCar shoppingCar);

    //判断该用户的购物车是否存在
    String queryUserCar(int goodsID,  String userID);

    //根据用户ID查找用户所属于的购User物车
    List<ShoppingCar> queryAllShoppingCar(String userID);

    //根据goodsId查找商品
    Product queryGoodsInfoID(int goodsID);

    //删除
    int dropShoppingCae(int goodsID);

    List<ShoppingCar> findByUid(Integer uid);
}
