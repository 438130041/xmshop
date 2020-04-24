package com.ly.shopping.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.shopping.pojo.Category;
import com.ly.shopping.pojo.Product;
import com.ly.shopping.pojo.Result;
import com.ly.shopping.service.CategoryService;
import com.ly.shopping.service.ProductService;
import com.ly.shopping.util.Page;
import com.ly.shopping.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/admin_listproduct")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> productList = productService.findAllProduct();
        System.out.println(productList);
        int total = (int) new PageInfo<>(productList).getTotal();
        page.setTotal(total);
        model.addAttribute("productList", productList);
        model.addAttribute("page", page);
        return "admin/listProduct";
    }

    @ResponseBody
    @RequestMapping("admin_product_add")
    public Result<Product> add(Product p, HttpSession hs) {
        p.setStatus(1);
        p.setCreateDate(new Date());
        String singleName = hs.getAttribute("singleName").toString();
        p.setProductimg(singleName);
        productService.insert(p);
        return new ResultUtil<Product>().setData(p);
    }


    @RequestMapping("admin_product_delete")
    public String delete(int id) {
        Product p = productService.get(id);
        productService.delete(id);
        return "redirect:admin_listproduct?cid="+p.getCid();
    }

    @RequestMapping("/editProduct")
    public String edit(Integer id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        Category category = categoryService.get(product.getCid());
        model.addAttribute("category", category);
        return "admin/editProduct";
    }

    @RequestMapping("/updateProduct")
    public String update1(Product product) {
        productService.update(product);
        return "redirect:admin_listproduct?cid=" + product.getCid();
    }


    @ResponseBody
    @RequestMapping("admin_product_edit")
    public Result<Product> edit(Product p, HttpSession hs) {
        p.setStatus(1);
        productService.update(p);
        return new ResultUtil<Product>().setData(p);
    }

    @RequestMapping("admin_updateproduct")
    public String update(Product p) {
        productService.update(p);
        return "redirect:admin_listproduct?cid="+p.getCid();
    }
    @ResponseBody()
    @RequestMapping(value = "admin_updateStatus/{id}/{status}")
    public String updateStatus(@PathVariable int id,
                               @PathVariable int status){

        Product p = new Product();
        p.setId(id);
        if (status==0){
            p.setStatus(1);
            productService.update(p);
        }
        if (status==1){
            p.setStatus(0);
            productService.update(p);
        }
        return "success";

    }

}
