package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.LearnRecord;
import com.ycjw.minesecurity.model.LearnResource;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.repository.LearnRecordRepository;
import com.ycjw.minesecurity.service.LearnRecordService;
import com.ycjw.minesecurity.service.LearnResourceService;
import com.ycjw.minesecurity.utils.KeyUtil;
import com.ycjw.minesecurity.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class LearnRecordServiceImpl implements LearnRecordService {

    @Autowired
    private LearnRecordRepository learnRecordRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response createLearnRecord(String phoneNum,int timeLong,String resourceId) throws Exception {
        if (phoneNum==null || timeLong<=0|| resourceId==null){
            return new Response("失败",null);
        }
        LearnRecord learnRecord=new LearnRecord();
        learnRecord.setLearnDate(new Date());
        learnRecord.setTimeLong(timeLong);
        learnRecord.setLearnRecordId(KeyUtil.getUniqueKey_15());
        learnRecord.setPhoneNum(phoneNum);
        learnRecord.setResourceId(resourceId);
        //保存
        LearnRecord result=learnRecordRepository.save(learnRecord);
        if (result==null){
            LogUtil.logger.error("【创建学习记录】---失败--learnRecord={"+learnRecord.toString()+"}");
            throw new ExceptionZyc(new Response("失败",null));
        }
        return new Response("成功",null);
    }

    @Override
    public Response findAllByUserPhone(String phone) {

        return null;
    }
}
