package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.service.LearnResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/learn_resource")
public class LearnController {

    @Autowired
    private LearnResourceService learnResourceService;

    /**
     * 创建学习资源
     * @param resourceContent 文档资源
     * @param resourceName  资源名称
     * @param image  图片资源
     * @return
     * @throws Exception
     */
    @ApiOperation("创建学习资源")
    @PostMapping("/create_resource")
    public Response create_resource(@RequestParam(required = true,value = "resourceContent") MultipartFile resourceContent,
                                    @RequestParam(required = true,value = "resourceName") String resourceName,
                                    @RequestParam(required = true,value = "image")MultipartFile image)throws Exception{
        return learnResourceService.create_resource(resourceContent,resourceName,image);
    }

}
