package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.service.ResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class ResourceController {
    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @ApiOperation("上传资源")
    @PostMapping("uploadResource")
    public Response uploadResource(@RequestParam(value = "resourceCover",required = false) MultipartFile resourceCover,
                                   @RequestParam("resourceContent") MultipartFile resourceContent,
                                   @RequestParam("filename") String filename) throws ExceptionZyc {
        return new Response("上传成功",resourceService.uploadResource(resourceCover, resourceContent, filename));
    }

    @ApiOperation("下载资源")
    @GetMapping("downloadResource")
    public void downloadResource(@RequestParam("resourceId") String resourceId, HttpServletResponse response) throws ExceptionZyc {
        resourceService.downloadResource(resourceId,response);
    }

    @ApiOperation("查询所有资源")
    @GetMapping("/find_all_resource")
    public Response findAllResource() throws Exception{
        return resourceService.findAllResource();
    }
}
