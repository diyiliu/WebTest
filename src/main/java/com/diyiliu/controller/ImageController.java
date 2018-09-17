package com.diyiliu.controller;

import com.diyiliu.util.JacksonUtil;
import com.jhlabs.image.CropFilter;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: ImageController
 * Author: DIYILIU
 * Update: 2018-09-17 14:50
 */

@Controller
@RequestMapping("/img")
public class ImageController {


    @Resource
    private Environment environment;

    @RequestMapping
    public String image(){

        return "image";
    }

    @ResponseBody
    @PostMapping("/upload")
    public Integer onUpload(MultipartFile file, HttpServletRequest request) throws Exception {
        if (file.isEmpty()) {

            return 0;
        }

        String data = request.getParameter("data");
        Map dataMap = JacksonUtil.toObject(data, HashMap.class);
        String fileName = (String) dataMap.get("name");

        String picDir = environment.getProperty("upload.pic-dir");
        org.springframework.core.io.Resource resDir = new UrlResource(picDir);

        // 创建临时文件
        // File tempFile = File.createTempFile("pic", fileExtension, resDir.getFile());
        File tempFile =  new File(resDir.getFile() + File.separator + "pic_" + fileName);
        FileCopyUtils.copy(file.getBytes(), tempFile);
        // 剪切图片
        cutPic(tempFile, dataMap);

        return 1;
    }

    private void cutPic(File file, Map data) throws Exception{
        Double x = (Double) data.get("x");
        Double y = (Double) data.get("y");
        Double w = (Double) data.get("width");
        Double h = (Double) data.get("height");

        BufferedImage fromImage = ImageIO.read(file);
        BufferedImage toImage = new BufferedImage(w.intValue(), h.intValue(), BufferedImage.TYPE_INT_RGB);
        CropFilter cropFilter = new CropFilter(x.intValue(), y.intValue(), w.intValue(), h.intValue());
        cropFilter.filter(fromImage, toImage);
        // 写回指定目标文件
        ImageIO.write(toImage, "jpg", file);
    }
}
