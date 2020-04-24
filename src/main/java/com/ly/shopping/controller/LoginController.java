package com.ly.shopping.controller;

import com.ly.shopping.pojo.Admin;
import com.ly.shopping.pojo.Product;
import com.ly.shopping.pojo.Result;
import com.ly.shopping.pojo.ShoppingCar;
import com.ly.shopping.service.AdminService;
import com.ly.shopping.service.ShoppingCarService;
import com.ly.shopping.service.UserService;
import com.ly.shopping.util.GeetestLib;
import com.ly.shopping.util.MD5Utils;
import com.ly.shopping.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCarService shoppingCarService;

    private ShoppingCar shoppingCar = new ShoppingCar();
    @RequestMapping("/loginPage")
    public String forelogin(){
        return "fore/loginPage";
    }
    @RequestMapping("/registerPage")
    public String register(){
        return "fore/registerPage";
    }
    @RequestMapping("registerSuccessPage")
    public String registerSuccessPage() {
        return "fore/registerSuccess";
    }

   /* @RequestMapping("/admin")
    public @ResponseBody Admin login(String name, String password, Model model, HttpSession session) {
        Admin admin = new Admin();
        admin.setName(name);
        admin.setPassword(password);
        admin = adminService.AdminLogin(admin);
        return admin;
    } */
    @RequestMapping(value="/adminlogin" ,method = RequestMethod.GET)
    public String tologin(){

        return "admin/login";
    }
    @RequestMapping("/main")
    public String main(){

        return "admin/main";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:adminlogin";
    }
    //极验初始化
    @ResponseBody
    @RequestMapping(value = "/geetestInit",method = RequestMethod.GET)
    public String geetesrInit(HttpServletRequest request){

        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);

        String resStr = "{}";

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);

        resStr = gtSdk.getResponseStr();

        return resStr;
    }

    //管理员登陆
    @ResponseBody
    @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
    public Result<Object> login(String name, String password,
                                String challenge, String validate, String seccode,
                                HttpServletRequest request){

        //极验验证
        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }
//        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        if (gtResult == 1) {
            // 验证成功
            name = HtmlUtils.htmlEscape(name);
            Admin admin = adminService.get(name,password);

            if(admin==null){
                return new ResultUtil<Object>().setErrorMsg("用户名或密码错误");
            }
            else {
                request.getSession().setAttribute("admin",admin);
                return new ResultUtil<Object>().setData(null);
            }
            //MD5加密
        }
        else {
            // 验证失败
            return new ResultUtil<Object>().setErrorMsg("验证失败");
        }
    }

}