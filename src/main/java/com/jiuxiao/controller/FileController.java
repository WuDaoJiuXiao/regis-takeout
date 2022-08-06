package com.jiuxiao.controller;

import com.jiuxiao.commons.RespBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传下载控制器
 * @Author: 悟道九霄
 * @Date: 2022年08月06日 13:51
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/common")
public class FileController {

    @Value("${regis.path}")
    private String basePath;

    /**
     * @param file
     * @return: com.jiuxiao.commons.RespBean<java.lang.String>
     * @decription 上传文件
     * @date 2022/8/6 14:09
     */
    @PostMapping("/upload")
    public RespBean<String> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;
        File dir = new File(basePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return RespBean.success(fileName);
    }

    /**
     * @param name
     * @param response
     * @return: void
     * @decription 文件下载
     * @date 2022/8/6 14:22
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws Exception {
        int len = 0;
        byte[] bytes = new byte[1024];

        //输入流获取文件名
        FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
        response.setContentType("image/jpeg");
        //输出流写入图片到浏览器
        ServletOutputStream outputStream = response.getOutputStream();
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }

        //关闭流
        outputStream.close();
        fileInputStream.close();
    }
}