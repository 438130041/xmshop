

package com.ly.shopping.service;

import com.ly.shopping.pojo.Order;
import com.ly.shopping.pojo.OrderItem;

import java.util.List;

public interface OrderService {


    void add(Order c);
    float add(Order c, List<OrderItem> ois);
    void delete(int id);
    void update(Order c);
    Order get(int id);
    List list();
    List list(int uid, String excludedStatus);


    //　供后台管理员进行　查看历史订单未发货订单
    List list_By_Admin(String status);

}
