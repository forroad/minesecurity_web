package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.Resource;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.model.util.FileUtil;
import com.ycjw.minesecurity.repository.ResourceDao;
import com.ycjw.minesecurity.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    private ResourceDao resourceDao;

    @Autowired
    public ResourceServiceImpl(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public Resource uploadResource(MultipartFile resourceCover, MultipartFile resourceContent,String filename) throws ExceptionZyc {
        Resource resource = new Resource();
        resource.setResourceId(System.currentTimeMillis() + "");
        //判断文件是否为空
        if (resourceCover == null||resourceCover.isEmpty()) {
            //文件封面为空
            resource.setCoverPath("C:\\minesecurity\\resource\\img\\default_cover.jpg");
        }else {
            resource.setCoverPath(FileUtil.uploadFile(resourceCover,"C:\\minesecurity\\resource\\img\\",resource.getResourceId()));
        }
        resource.setResourcePath(FileUtil.uploadFile(resourceContent,"C:\\minesecurity\\resource\\content\\",resource.getResourceId()));
        resource.setResourceName(filename);
        return resourceDao.save(resource);
    }

    @Override
    public void downloadResource(String resourceId, HttpServletResponse response) throws ExceptionZyc {
        //参数不能为空
        if(StringUtils.isEmpty(resourceId)){
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //判断资源是否存在
        Resource resource = resourceDao.findByResourceId(resourceId);
        if(resource == null){
            //资源不存在
            throw ExceptionZyc.RESOURCE_IS_NOT_EXIST;
        }
        FileUtil.downloadFile(resource.getResourcePath(),response);
    }

    @Override
    public Response findAllResource() throws Exception {
        List<Resource> resourceList=resourceDao.findAll();
        if (resourceList==null){
            return new Response("失败",resourceList);
        }
        return new Response("成功",resourceList);
    }

    @Override
    public Response findCover(String resourceId, HttpServletResponse response) throws Exception {
        //参数不能为空
        if(StringUtils.isEmpty(resourceId)){
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //判断资源是否存在
        Resource resource = resourceDao.findByResourceId(resourceId);
        if(resource == null){
            //资源不存在
            throw ExceptionZyc.RESOURCE_IS_NOT_EXIST;
        }
        FileUtil.loadImg(resource.getCoverPath(),response);
        return null;
    }
}
