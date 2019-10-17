package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.enums.ResourcesPath;
import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.LearnResource;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.repository.LearnResourceDao;
import com.ycjw.minesecurity.service.LearnResourceService;
import com.ycjw.minesecurity.utils.KeyUtil;
import com.ycjw.minesecurity.utils.MultipartUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class LearnResourceServiceImpl implements LearnResourceService {

    @Autowired
    private LearnResourceDao learnResourceDao;

    @Override
    public LearnResource findOneById(String id) {
        return learnResourceDao.findById(id).orElse(null);
    }

    @Override
    public List<LearnResource> findAll() {
        return learnResourceDao.findAll();
    }

    @Override
    public LearnResource saveOne(LearnResource learnResource){
        return learnResourceDao.save(learnResource);
    }

    /**
     * 创建学习资源
     * @param resourceContent 资源文本内容
     * @param resourceName 资源名称
     * @param image 资源图片
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response create_resource(MultipartFile resourceContent, String resourceName, MultipartFile image) throws Exception{
        //1.检查参数
        if(resourceContent==null || resourceName==null || image==null){
          throw ExceptionZyc.PARAM_IS_NULL;
        }
        //2先保存资源对象
        String imageName= KeyUtil.getUniqueKey_15()+MultipartUtil.getFileType(image);
        String docName=KeyUtil.getUniqueKey_15()+MultipartUtil.getFileType(resourceContent);
        LearnResource learnResource=new LearnResource();
        learnResource.setName(resourceName);
        learnResource.setImageUrl(imageName);
        learnResource.setResourcePath(docName);
        learnResource.setResourceId(KeyUtil.getUniqueKey_15());
        //保存
        LearnResource result=learnResourceDao.save(learnResource);
        if (result==null){
            log.error("【创建学习资源】----保存失败--learnResource={}",learnResource);
            throw new ExceptionZyc(new Response("创建资源失败，请再次尝试！",null));
        }
        //1.如果成功再上传图片，图片上传失败进行事务回滚
        boolean imgRe= MultipartUtil.upLoadImage(image,imageName,ResourcesPath.LEARN_RESOURCE_IMAGE_PATH.getResourcePrefix());
        boolean docRe=MultipartUtil.upLoadImage(resourceContent,docName,ResourcesPath.LEARN_RESOURCE_DOC_PATH.getResourcePrefix());
        if (!imgRe){
            //图片上传失败
            log.error("【创建学习资源】---图片上传失败---完整路径={}",ResourcesPath.LEARN_RESOURCE_IMAGE_PATH.getResourcePrefix()+imageName);
            throw new ExceptionZyc(new Response("图片上传失败！请再次尝试！",null) );
        }
        if (!docRe){
            log.error("【创建学习资源】---文档上传失败---完整路径={}",ResourcesPath.LEARN_RESOURCE_DOC_PATH.getResourcePrefix()+imageName);
            throw new ExceptionZyc(new Response("文档上传失败！请再次尝试！",null) );
        }
        return new Response("创建成功！",null) ;
    }

}
