package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.Resource;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("user")
public class TestController {
    @ApiOperation("测试下载文件")
    @GetMapping(value = "/testDownload",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getUserHeadImg(HttpServletResponse response, HttpServletRequest request) throws ExceptionZyc {
        try {
            File file = new File("C:\\Users\\17127\\Documents\\Tencent Files\\1712767833\\FileRecv\\矿山安全培训需求和设计.docx");
            //判断文件是否存在如果不存在就返回默认图标
            if(!(file.exists() && file.canRead())) {
                //用户不存在，抛出错误
                throw ExceptionZyc.IMAGE_URL_IS_NULL;
            }
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "utf-8"));
            System.out.println(file.getName());
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
