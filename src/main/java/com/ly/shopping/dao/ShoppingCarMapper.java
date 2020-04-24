package com.ly.shopping.dao;
import com.ly.shopping.pojo.Product;
import com.ly.shopping.pojo.ShoppingCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCarMapper {
    //点击增加购物车，商品数量默认为1
    void addShoppingCar(ShoppingCar shoppingCar);

    //判断该用户的购物车是否存在
     String queryUserCar(@Param("goodsID") int goodsID, @Param("userID") String userID);

    //根据用户ID查找用户所属于的购User物车
     List<ShoppingCar> queryAllShoppingCar(@Param("userID") String userID);

    //根据goodsId查找商品
     Product queryGoodsInfoID(@Param("goodsID") int goodsID);

    //删除
     int dropShoppingCae(int goodsID);

    List<ShoppingCar> findByUid(@Param("uid") Integer uid);
}
