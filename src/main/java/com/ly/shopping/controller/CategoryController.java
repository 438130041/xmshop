package com.ly.shopping.controller;

import com.ly.shopping.pojo.Category;
import com.ly.shopping.pojo.Result;
import com.ly.shopping.service.CategoryService;
import com.ly.shopping.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("getCategory")
    public String getCategory(Model model){
        List<Category> cs= categoryService.list();
        model.addAttribute("cs", cs);
        return "admin/addproduct";
    }

    @RequestMapping("toCategory")
    public String toCategory(Model model){
        List<Category> cs= categoryService.list();
        model.addAttribute("cs", cs);
        return "admin/admin_managerCategory";
    }

    @ResponseBody
    @RequestMapping("admin_category_add")
    public Result<Category> add(Category c) {
        List<Category> cs= categoryService.list();
        for (Category cg:cs)
            if (cg.getName().equals(c.getName()))
                return new ResultUtil<Category>().setErrorMsg("分类已存在！");
        categoryService.add(c);
        return new ResultUtil<Category>().setData(c);
    }


    @RequestMapping("/toEditCategory")
    public String toEditCategory(Model model,int id){
        Category c = categoryService.get(id);
        model.addAttribute("cg",c);
        return "admin/editCategory";
    }

    @ResponseBody
    @RequestMapping("admin_category_edit")
    public Result<Category> edit(Category c,Model model) {
        List<Category> cs= categoryService.list();
        for (Category cg:cs)
            if (cg.getName().equals(c.getName()))
                return new ResultUtil<Category>().setErrorMsg("分类已存在！");
        categoryService.update(c);
        model.addAttribute("c", c);
        return new ResultUtil<Category>().setData(c);
    }
}
