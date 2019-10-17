package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.Resource;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ResourceService {
    /**
     *  上传学习资源
     * @param resourceCover 资源封面
     * @param resourceContent 资源内容
     * @return 资源信息
     * @throws ExceptionZyc
     */
     Resource uploadResource(MultipartFile resourceCover, MultipartFile resourceContent,String filename) throws ExceptionZyc;

    /**
     * 下载资源文件
     * @param resourceId 资源id
     * @throws ExceptionZyc
     */
     void downloadResource(String resourceId, HttpServletResponse response) throws ExceptionZyc;
}
