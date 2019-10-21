package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.model.LearnRecord;
import com.ycjw.minesecurity.model.response.Response;

import java.util.List;

public interface LearnRecordService {

     Response createLearnRecord(String phoneNum,int timeLong,String resourceId) throws Exception;

     Response  findAllByUserPhone(String phone) throws Exception;

     Response countLearnRecord(String phoneNum)throws Exception;

     long  getTimeSum(String phone) throws Exception;

     List<LearnRecord> findSomeResourceIdByPhone(String phone, int size,int pageNum) throws Exception;

}
