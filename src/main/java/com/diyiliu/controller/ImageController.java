package com.diyiliu.controller;

import com.diyiliu.util.JacksonUtil;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
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
        String path = resDir.getFile() + File.separator + "pic_" + fileName;
        File tempFile =  new File(path);
        FileCopyUtils.copy(file.getBytes(), tempFile);
        // 剪切图片
        cutPic(path, dataMap);

        return 1;
    }

    private void cutPic(String path, Map data) throws Exception{
        int x =  new BigDecimal((double) data.get("x")).intValue();
        int y =  new BigDecimal((double) data.get("y")).intValue();
        int w =  new BigDecimal((double) data.get("width")).intValue();
        int h =  new BigDecimal((double) data.get("height")).intValue();

        ImagePlus imp = IJ.openImage(path);
        imp.setRoi(x, y, w, h);
        imp = imp.duplicate();

        ImageProcessor processor = imp.getProcessor().resize(128, 128);
        imp.setProcessor(processor);
        IJ.save(imp, path);
    }
}
