package com.ly.shopping.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.shopping.pojo.Order;
import com.ly.shopping.service.OrderItemService;
import com.ly.shopping.service.OrderService;
import com.ly.shopping.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private   OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;


    @RequestMapping("/admin_showAllOrder")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Order> os= orderService.list();
        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);
        orderItemService.fill(os);
        model.addAttribute("os", os);
        model.addAttribute("page", page);
        return  "admin/admin_listOrder";

    }

    @RequestMapping("/admin_order_delivery")
    public String delivery(Order o) throws IOException {
        o.setDeliveryDate(new Date());
     //   o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return "redirect:admin_order_list";
    }
    String s=null;

    @RequestMapping("/list_By_Admin")
    public String list_By_Admin(@RequestParam(value="status",required=false) String status, Model model, Page page) throws IOException {
        PageHelper.offsetPage(page.getStart(),page.getCount());
        if(status==null){
            status=s;
        }

        System.out.println("获取到：status:"+status);
        List<Order> os= orderService.list_By_Admin(status);
        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);
        orderItemService.fill(os);
        s=status;
        model.addAttribute("os", os);
        model.addAttribute("page", page);

        model.addAttribute("s", status);
        return  "admin/admin_listOrder";
    }

    @RequestMapping("/admin_send_Order")
    public String send_order( Model model,@RequestParam("id") int oid) {
        System.out.println(oid);
        Order o = orderService.get(oid);
      //  o.setStatus(OrderService.waitConfirm);

        o.setDeliveryDate(new Date());
        orderService.update(o);
        return "redirect:admin_showAllOrder";
    }

    @RequestMapping("/delete_Order")
    public String delete_Order( Model model,@RequestParam("oid") int oid) {
        System.out.println(oid);
        Order o = orderService.get(oid);
       // o.setStatus(OrderService.delete);
        o.setConfirmDate(new Date());
        orderService.update(o);

        return "redirect:forebought";
    }

    @RequestMapping("/admin_show_sinle_order")
    public String single_order( Model model,@RequestParam("id") int oid) {
        System.out.println(oid);
        Order o = orderService.get(oid);



        model.addAttribute("single_order",o);
        return "admin/admin_Order_Detail";

    }
}
