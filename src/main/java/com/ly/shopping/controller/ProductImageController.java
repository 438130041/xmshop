package com.ly.shopping.controller;
import com.ly.shopping.pojo.KindEditorResult;
import com.ly.shopping.pojo.Product;
import com.ly.shopping.pojo.Result;
import com.ly.shopping.service.ProductImageService;
import com.ly.shopping.service.ProductService;
import com.ly.shopping.util.ImageUtil;
import com.ly.shopping.util.ResultUtil;
import com.ly.shopping.util.getExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;


@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    private static final Logger log = LoggerFactory.getLogger(ProductImageController.class);

    //WebUploader图片上传
    @ResponseBody
    @RequestMapping(value = "/image/imageUpload")
    public Result<Object> uploadFile(@RequestParam("file") MultipartFile files, HttpSession hs) {
        String fileName = files.getOriginalFilename();
        String imageFolder = hs.getServletContext().getRealPath("img/goodsInfo/");
        File f = new File(imageFolder, fileName);
        if (f.exists()){
            return new ResultUtil<Object>().setErrorMsg("图片已存在，请改名后重新上传！");
        }
        String imagePath="";
        //获取父目录
        f.getParentFile().mkdirs();
        //获取文件扩展名
        String ex = getExtension.extension(fileName);
        try {
            files.transferTo(f);
            BufferedImage img = ImageUtil.changeImage(f);
            ImageIO.write(img, ex, f);
            ImageUtil.resizeImage(f, 217, 190, f);
            hs.setAttribute("singleName",fileName);
            imagePath = imageFolder+fileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResultUtil<Object>().setData(imagePath);
    }

    @ResponseBody
    @RequestMapping(value = "/editImage/imageUpload")
    public Result<Object> uploadImage(@RequestParam("file") MultipartFile files, HttpSession hs) {
        Product p = (Product)hs.getAttribute("p");
        String fileName = files.getOriginalFilename();
        String imageFolder = hs.getServletContext().getRealPath("img/goodsInfo/");
        //删除以前的缩略图
        if (p!=null){
            File file = new File(imageFolder,p.getProductimg());
            if (file.exists()&&file.isFile())
                file.delete();
        }
        File f = new File(imageFolder, fileName);
        if (f.exists()){
            return new ResultUtil<Object>().setErrorMsg("图片已存在，请改名后重新上传！");
        }
        String imagePath="";
        //获取父目录
        f.getParentFile().mkdirs();
        //获取文件扩展名
        String ex = getExtension.extension(fileName);
        try {
            files.transferTo(f);
            BufferedImage img = ImageUtil.changeImage(f);
            ImageIO.write(img, ex, f);
            ImageUtil.resizeImage(f, 217, 190, f);
            hs.setAttribute("singleName",fileName);
            imagePath = imageFolder+fileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResultUtil<Object>().setData(imagePath);
    }

}

