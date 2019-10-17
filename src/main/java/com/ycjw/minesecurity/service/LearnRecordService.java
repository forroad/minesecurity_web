package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.model.response.Response;

public interface LearnRecordService {

     Response createLearnRecord(String phoneNum,int timeLong,String resourceId) throws Exception;

     Response  findAllByUserPhone(String phone) throws Exception;

     Response countLearnRecord(String phoneNum)throws Exception;

}
