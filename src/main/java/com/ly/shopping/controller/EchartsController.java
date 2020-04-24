package com.ly.shopping.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.shopping.pojo.Category;
import com.ly.shopping.pojo.OrderGroup;
import com.ly.shopping.pojo.Product;
import com.ly.shopping.service.CategoryService;
import com.ly.shopping.service.OrderService;
import com.ly.shopping.service.ProductService;
import com.ly.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EchartsController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;


    @RequestMapping("go_chart1")
    public String  forechart1(Model model, @RequestParam("cid") int id, HttpSession session) {

        System.out.println(id);
        Category cc = categoryService.get(id);
        productService.fill(cc);
        productService.setSaleAndReviewNumber(cc.getProducts());

        Map<String,Integer> map1 = new HashMap();


        for (Product pp : cc.getProducts()) {

            map1.put(pp.getProduct_name(),pp.getStock());


        }

        session.setAttribute("map1",map1);

//
//            float arr[]={50, 20, 36, 10, 10, 30};
//
//            session.setAttribute("shuju",arr);


        session.setAttribute("admin_showProduct","admin_showProduct");
        model.addAttribute("cc", cc);

        return "admin/admin_chart1";

    }






    @RequestMapping("go_chart3")
    public String  forechart3( Model model,@RequestParam("cid") int id,HttpSession session) {

        System.out.println(id);
        Category cc = categoryService.get(id);
        productService.fill(cc);
        productService.setSaleAndReviewNumber(cc.getProducts());
        Map<String,Integer> map3 = new HashMap();
        for (Product pp : cc.getProducts()) {
            map3.put(pp.getProduct_name(),pp.getReviewCount());
        }
        session.setAttribute("map3",map3);
        session.setAttribute("admin_showProduct","admin_showProduct");
        model.addAttribute("cc", cc);

        return "admin/admin_chart3";

    }




    @RequestMapping("go_chart2")
    public String  forechart2( Model model,@RequestParam("cid") int id,HttpSession session) {

        System.out.println(id);
        Category cc = categoryService.get(id);
        productService.fill(cc);
        productService.setSaleAndReviewNumber(cc.getProducts());

        Map<String,Integer> map2 = new HashMap();


        for (Product pp : cc.getProducts()) {

            map2.put(pp.getProduct_name(),pp.getSaleCount());


        }



        session.setAttribute("map2",map2);


        float arr[]={50, 20, 36, 10, 10, 30};

        session.setAttribute("shuju",arr);


        session.setAttribute("admin_showProduct","admin_showProduct");
        model.addAttribute("cc", cc);

        return "admin/admin_chart2";

    }












    @RequestMapping(value = "admin/home", method = RequestMethod.GET)
    public String goToPageByAjax(HttpSession session, Map<String, Object> map) throws ParseException {
       // logger.info("获取统计信息");
        Integer productTotal = productService.getTotal(null, new Byte[]{0, 2});
        Integer userTotal = userService.getTotal(null);
     //   Integer orderTotal = orderService.getTotal(null, new Byte[]{3});
       // logger.info("获取图表信息");
        map.put("jsonObject", getChartData(null, null,7));
      //  logger.info("获取图表信息");
        map.put("jsonObject", getChartData(null,null,7));
        map.put("productTotal", productTotal);
        map.put("userTotal", userTotal);
     //   map.put("orderTotal", orderTotal);
      //  logger.info("转到后台管理-主页-ajax方式");
        return "admin/homeManagePage";
    }
    /**
     * 按日期查询图表数据（ajax方式）
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 响应数据
     * @throws ParseException 转换异常
     */
    @ResponseBody
    @RequestMapping(value = "admin/home/charts", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getChartDataByDate(@RequestParam(required = false) String beginDate, @RequestParam(required = false) String endDate) throws ParseException {
        if (beginDate != null && endDate != null) {
            //转换日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return getChartData(simpleDateFormat.parse(beginDate), simpleDateFormat.parse(endDate),7).toJSONString();
        } else {
            return getChartData(null, null,7).toJSONString();
        }
    }

    /**
     * 按日期获取图表数据
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param days 天数
     * @return 图表数据的JSON对象
     * @throws ParseException 转换异常
     */
    private JSONObject getChartData(Date beginDate, Date endDate, int days) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        SimpleDateFormat time2 = new SimpleDateFormat("MM/dd", Locale.UK);
        SimpleDateFormat timeSpecial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
        //如果没有指定开始和结束日期
        if (beginDate == null || endDate == null) {
            //指定一周前的日期为开始日期
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1-days);
            beginDate = time.parse(time.format(cal.getTime()));
            //指定当前日期为结束日期
            cal = Calendar.getInstance();
            endDate = cal.getTime();
        } else {
            beginDate = time.parse(time.format(beginDate));
            endDate = timeSpecial.parse(time.format(endDate) + " 23:59:59");
        }
       // logger.info("根据订单状态分类");
        //未付款订单数统计数组
        int[] orderUnpaidArray = new int[7];
        //未发货订单数统计叔祖
        int[] orderNotShippedArray = new int[7];
        //未确认订单数统计数组
        int[] orderUnconfirmedArray = new int[7];
        //交易成功订单数统计数组
        int[] orderSuccessArray = new int[7];
        //总交易订单数统计数组
        int[] orderTotalArray = new int[7];
      //  logger.info("从数据库中获取统计的订单集合数据");
    //    List<OrderGroup> orderGroupList = orderService.getTotalByDate(beginDate, endDate);
        //初始化日期数组
        JSONArray dateStr = new JSONArray(days);
        //按指定的天数进行循环
        for (int i = 0; i < days; i++) {
            //格式化日期串（MM/dd）并放入日期数组中
            Calendar cal = Calendar.getInstance();
            cal.setTime(beginDate);
            cal.add(Calendar.DATE, i);
            String formatDate = time2.format(cal.getTime());
            dateStr.add(formatDate);
            //该天的订单总数
            int orderCount = 0;
            //循环订单集合数据的结果集
           // for(int j = 0; j < orderGroupList.size(); j++){
           //     OrderGroup orderGroup = orderGroupList.get(j);
                //如果该订单日期与当前日期一致
              //  if(orderGroup.getPayDate().equals(formatDate)){
                    //从结果集中移除数据
                //    orderGroupList.remove(j);
                    //根据订单状态将统计结果存入对应的订单状态数组中
                 //   switch (orderGroup.getStatus()) {
                 //       case 0:
                            //未付款订单
                   //         orderUnpaidArray[i] = orderGroup.getProductOrder_count();
                            break;
                   //     case 1:
                            //未发货订单
                     //       orderNotShippedArray[i] = orderGroup.getProductOrder_count();
                          //  break;
                    //    case 2:
                            //未确认订单
                     //       orderUnconfirmedArray[i] = orderGroup.getProductOrder_count();
                         //   break;
                   //     case 3:
                            //交易成功订单
                    //        orderSuccessArray[i] = orderGroup.getProductOrder_count();
                          //  break;
                    }
                    //累加当前日期的订单总数
                  //  orderCount += orderGroup.getProductOrder_count();
             //   }
        //    }
            //将统计的订单总数存入总交易订单数统计数组
          //  orderTotalArray[i] = orderCount;
        //}
        //logger.info("返回结果集map");
        jsonObject.put("orderTotalArray", orderTotalArray);
        jsonObject.put("orderUnpaidArray", orderUnpaidArray);
        jsonObject.put("orderNotShippedArray", orderNotShippedArray);
        jsonObject.put("orderUnconfirmedArray", orderUnconfirmedArray);
        jsonObject.put("orderSuccessArray", orderSuccessArray);
        jsonObject.put("dateStr",dateStr);
        return jsonObject;
    }


}
