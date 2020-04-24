package com.ly.shopping.service.impl;

import com.ly.shopping.dao.ProductMapper;
import com.ly.shopping.dao.ShoppingCarMapper;
import com.ly.shopping.pojo.Product;
import com.ly.shopping.pojo.ShoppingCar;
import com.ly.shopping.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {
    @Autowired
    private ShoppingCarMapper shoppingCarMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public void addShoppingCar(ShoppingCar shoppingCar) {
        shoppingCarMapper.addShoppingCar(shoppingCar);

    }

    @Override
    public String queryUserCar(int goodsID, String userID) {

        return shoppingCarMapper.queryUserCar(goodsID,userID);
    }

    @Override
    public List<ShoppingCar> queryAllShoppingCar(String userID) {
        return shoppingCarMapper.queryAllShoppingCar(userID);
    }

    @Override
    public Product queryGoodsInfoID(int goodsID) {

        return shoppingCarMapper.queryGoodsInfoID(goodsID);
    }

    @Override
    public int dropShoppingCae(int goodsID) {
        return shoppingCarMapper.dropShoppingCae(goodsID);
    }

    @Override
    public List<ShoppingCar> findByUid(Integer uid)
    {
        List<ShoppingCar> carts = shoppingCarMapper.findByUid(uid);
        for (ShoppingCar cart : carts) {
            cart.setProduct(productMapper.findByProductId(cart.getGoodsID()));
        }
        return carts;
    }
    }

