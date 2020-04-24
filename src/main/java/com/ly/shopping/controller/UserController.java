package com.ly.shopping.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.shopping.pojo.User;
import com.ly.shopping.service.UserService;
import com.ly.shopping.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/admin_listuser")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> userlist= userService.list();
        int total = (int) new PageInfo<>(userlist).getTotal();
        page.setTotal(total);
        model.addAttribute("userlist", userlist);
        model.addAttribute("page", page);
        return "admin/listUser";
    }
    //模糊查询
    @RequestMapping("/admin_user_list_like")
    public String alike_search(Model model, Page page, @RequestParam("find_value") String find_value, @RequestParam("search_input") String input_value){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        System.out.println("获得："+find_value);
        System.out.println(input_value);
        if(find_value.equals("All")){
            return "redirect:admin_listuser";
        }
        List<User> userlist= userService.alikeList(find_value,input_value);
        int total = (int) new PageInfo<>(userlist).getTotal();
        page.setTotal(total);
        model.addAttribute("userlist", userlist);
        model.addAttribute("page", page);
        return "admin/listUser";
    }
    @RequestMapping("/admin_updateUser")
    public String updateUser(User user, HttpServletRequest request, Model model){
        userService.update(user);
        user=userService.get(user.getId());
        request.setAttribute("user", user);
        model.addAttribute("user", user);
        return "redirect:admin_listuser";
    }
    @RequestMapping("/admin_getUser")
    public String getUser(int id,User user,HttpServletRequest request,Model model){
        request.setAttribute("user",userService.get(id));
        model.addAttribute("user", userService.get(id));
        return "admin/editUser";
    }
    @RequestMapping("/admin_delUser")
    public String delUser(int id,HttpServletRequest request){
        userService.get(id);
        return "redirect:admin_listuser";

    }
}
