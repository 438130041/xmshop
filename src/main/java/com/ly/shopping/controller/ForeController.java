package com.ly.shopping.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ly.shopping.pojo.*;
import com.ly.shopping.service.*;
import com.ly.shopping.util.AlipayConfig;
import com.ly.shopping.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ForeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private EditionsService editionsService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private StatService statService;
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCarService shoppingCarService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ReviewService reviewService;
    private ShoppingCar shoppingCar = new ShoppingCar();
    private Product product = new Product();

    @RequestMapping("/index")
    public ModelAndView listAll() {
        ModelAndView mv = new ModelAndView("fore/index");
        mv.addObject("goodsAll", productService.findAllProduct());
        /*查询小米明星单品*/
        List<Integer> li = statService.queryAllStar();
        List<Product> lg = new ArrayList<Product>();
        for (Integer i : li) {
            lg.add(productService.queryGoods(i));
        }
        mv.addObject("starAll", lg);
        mv.addObject("carousel", carouselService.querycarousel());
        return mv;
    }

    @RequestMapping("/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session) throws IOException {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);
        if (null == user) {
            model.addAttribute("msg", "账号密码错误");
            return "fore/loginPage";
        }
        session.setAttribute("user", user);
        return "redirect:index";

    }

    @RequestMapping("/forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null != user)
            return "success";
        return "fail";
    }

    @RequestMapping("/foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);

        if (null == user) {
            return "fail";
        }
        session.setAttribute("user", user);
        return "success";
    }

    @RequestMapping("/logout1")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:index";
    }

    @RequestMapping("/register")
    public String register(Model model, User user) {
        String name = user.getName();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);

        boolean exist = userService.isExist(name);

        if (exist) {
            String m = "用户名已经被使用,不能使用";
            model.addAttribute("msg", m);
            return "fore/register";
        }
        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    @RequestMapping("/details")
    public ModelAndView querydetails(int goodsID) {
        ModelAndView mv = new ModelAndView("fore/details");
        mv.addObject("goodsAll", productService.findAllProduct());
        mv.addObject("edition", editionsService.queryedition(goodsID));
        mv.addObject("color", colorService.querycolor(goodsID));
        mv.addObject("img", productImageService.queryimg(goodsID));
        mv.addObject("moImg", productImageService.queryMoimg(goodsID));
        mv.addObject("lx", productService.querylx(goodsID));
        return mv;
    }


    //添加购物车
    @RequestMapping("/addShoppingCar")
    public ModelAndView addShoppingCar(int goodsID, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        System.out.println(goodsID);
        boolean b = true;
        ModelAndView mv = new ModelAndView("fore/addShoppingCar");
        mv.addObject("goodsAll", productService.findAllProduct());
        Product product = productService.queryGoods(goodsID);
        User user = (User) session.getAttribute("user");
        //判断是否登录，否则添加到Cookie
        if (user == null) {
            UUID uuid = UUID.randomUUID();
            List<Integer> lis = CookieUtils.queryAllCookie(request);
            for (int i : lis) {
                if (i == goodsID) {
                    b = false;
                    break;
                }
            }
            if (b) {
                product = productService.queryGoods(goodsID);
                CookieUtils.addCookie(response, uuid.toString(), product.getId() + "", 60000);
            } else {
                //System.out.println("该商品在Cookie中已存在");
            }
        } else {
            //判断该用户的购物车是否存在
            if (shoppingCarService.queryUserCar(goodsID, user.toString()) == null) {
                CookieUtils.replaceDao(shoppingCar, product);
                //用户
                shoppingCar.setUserID(session.getAttribute("user").toString());
                //添加到数据库
                shoppingCarService.addShoppingCar(shoppingCar);
            }
        }
        mv.addObject("goods", product);
        return mv;
    }


    //查看购物车
    @RequestMapping("/shoppingCar")
    public ModelAndView ShoppingCar(Integer goodsID, HttpSession session, HttpServletRequest request) {
        List<Product> li = new ArrayList<Product>();
        ModelAndView mv = new ModelAndView("fore/shoppingCar");
        mv.addObject("goodsAll", productService.findAllProduct());
        User user = (User) session.getAttribute("user");
        // 判断是否登录，否则查看Cookie
        if (session.getAttribute("user") == null) {
            //获取Cookie
            List<Integer> lis = CookieUtils.queryAllCookie(request);
            for (int i : lis) {
                li.add(shoppingCarService.queryGoodsInfoID(i));
            }
            mv.addObject("goods", li);
        } else {
            mv.addObject("goods", shoppingCarService.queryAllShoppingCar(user.toString()));
        }

        return mv;
    }
    @RequestMapping("/carNum")
    @ResponseBody
    public Integer Num(HttpSession session,HttpServletRequest request){
        if (session.getAttribute("user") == null) {
            Cookie[] cookies = request.getCookies();
            return cookies.length-1;
        }else{
            List<ShoppingCar> li=shoppingCarService.queryAllShoppingCar(session.getAttribute("user").toString());
            return li.size();
        }
    }
    @RequestMapping("/dropShoppingCar") @ResponseBody
    public String dropShoppingCar(Integer goodsID,HttpServletRequest request,HttpSession session) {
        List<Integer> lis = CookieUtils.queryAllCookie(request);
        Cookie[] cookies = request.getCookies();
        if(session.getAttribute("user")==null) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    try {
                        if (goodsID == Integer.valueOf(cookie.getValue())) {
                            cookie.setMaxAge(-1);
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }else{
            shoppingCarService.dropShoppingCae(goodsID);
        }
        return "delete";
    }

    @RequestMapping("/carNums")
    @ResponseBody
    public String carNum(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String num = "0";
        if (user != null) {
            List<ShoppingCar> li = shoppingCarService.queryAllShoppingCar(session.getAttribute("user").toString());
            num = li.size() + "";
        } else {
            List<Integer> ll = CookieUtils.queryAllCookie(request);
            num = ll.size() + "";
        }
        return num;
    }
    @RequestMapping("/percens")
    public ModelAndView orderss(){
        ModelAndView mv = new ModelAndView("fore/percens");
        mv.addObject("goodsAll",productService.findAllProduct());
        return mv;
    }
    @RequestMapping("/carIndexShow")
    @ResponseBody
    public List<Product> carIndexShow(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Product> li = new ArrayList<Product>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<ShoppingCar> ls = shoppingCarService.queryAllShoppingCar(session.getAttribute("user").toString());
            for (ShoppingCar sc : ls) {
                li.add(productService.queryGoods(sc.getGoodsID()));
            }
        } else {
            List<Integer> ll = CookieUtils.queryAllCookie(request);
            for (int goodsID : ll) {
                li.add(productService.queryGoods(goodsID));
            }
        }
        return li;
    }


    @RequestMapping("/updateUser")
    public String updateUser(Model model, HttpSession session,
                             @RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("password") String password,
                             @RequestParam("user_realname") String user_realname,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             @RequestParam("sex") String sex,
                             @RequestParam("address") String address) {
        System.out.println(name);
        System.out.println(address);


        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setUser_realname(user_realname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSex(sex);
        user.setAddress(address);
        userService.pencer_update(user);
        return "redirect:personalCenter";
    }

    /**
     * 用户中心表 　查看自己家的个人信息
     */
    @RequestMapping("/personalCenter")
    public String personalCenter(Model model, HttpSession session) {
        //获取到当前在线User
        User u = (User) session.getAttribute("user");
        System.out.println("user personalcenter 登录得到user" + u.getName());
        System.out.println("personalcenter.***　得到用户详情信息");
        User user = userService.get1(u.getName());
        System.out.println(user);
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        //得到用户详情
        return "fore/percen";
    }

    @RequestMapping("foreupdate")
    public String foreupdate(Model model, HttpSession session) {


        return "fore/up_user";
    }





  /*  @RequestMapping(value = "forealipay")
    public String goAlipay(Integer oid, Float total, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user =(User)  request.getSession().getAttribute("user");
        List<OrderItem> ot = orderItemService.getByoAndu(user.getId(),oid);
        String productName = ot.get(0).getProduct().getProduct_name();
        Order order = orderService.get(oid);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
    //    String out_trade_no = order.getOrderCode();
        //付款金额，必填
        String total_amount = String.valueOf(Float.valueOf(total));
        //订单名称，必填
        String subject = ot.get(0).getProduct().getProduct_name();
        //商品描述，可空
       // String body = order.getUserMessage();

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

      //  alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
              //  + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        return null;
    }*/
      @RequestMapping("forebuy")
    public ModelAndView rs(User user,String name,HttpSession session,String moneys,Model model){
    ModelAndView mv = new ModelAndView("fore/buy");
        mv.addObject("goodsAll",productService.findAllProduct());
        name = (String)session.getAttribute("name");
        mv.addObject("aas",userService.queryxmuser(name));
        System.out.println(moneys);
        mv.addObject("moneys",moneys);
    //System.out.println(u_name);
    //System.out.println(userDaoMapper.queryxmuser(u_name));
        return mv;
    }
    @RequestMapping("/yu")
    public ModelAndView orders(String name,HttpSession session){
        ModelAndView mv = new ModelAndView("fore/yu");
        mv.addObject("goodsAll",productService.findAllProduct());
        name = (String)session.getAttribute("name");
        mv.addObject("as",userService.queryxmuser(name));

        return mv;
    }
    @RequestMapping("Service")
    public String Services(){
        return "fore/Service";
    }

}
