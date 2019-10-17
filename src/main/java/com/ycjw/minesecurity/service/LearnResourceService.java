package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.model.LearnResource;
import com.ycjw.minesecurity.model.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LearnResourceService {

    LearnResource saveOne(LearnResource learnResource);

    LearnResource findOneById(String id);

    List<LearnResource> findAll();

    /**
     * 创建一个学习资源
     * @param resourceContent 资源文本内容
     * @param resourceName 资源名称
     * @param image 资源图片
     * @return
     */
    Response create_resource(MultipartFile resourceContent,
                             String resourceName, MultipartFile image)throws Exception;

}
