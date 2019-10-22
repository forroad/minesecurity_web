package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.TrainingBack;
import com.ycjw.minesecurity.model.User;
import com.ycjw.minesecurity.repository.TrainingBackDao;
import com.ycjw.minesecurity.repository.UserDao;
import com.ycjw.minesecurity.service.TrainingBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class TrainingBackServiceImpl implements TrainingBackService {
    private UserDao userDao;
    private TrainingBackDao trainingBackDao;

    @Autowired
    public TrainingBackServiceImpl(UserDao userDao, TrainingBackDao trainingBackDao) {
        this.userDao = userDao;
        this.trainingBackDao = trainingBackDao;
    }

    @Override
    public TrainingBack addTrainingBack(String telephone, String content) throws ExceptionZyc {
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(content)){
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        if(userDao.findByPhoneNum(telephone) == null){
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        TrainingBack trainingBack = new TrainingBack(System.currentTimeMillis() + "",telephone,content,new Date());
        return trainingBackDao.save(trainingBack);
    }

    @Override
    public List<TrainingBack> findTrainingBackByTelephone(String telephone) throws ExceptionZyc{
        if(StringUtils.isEmpty(telephone)){
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        if(userDao.findByPhoneNum(telephone) == null){
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        return trainingBackDao.findByTelephone(telephone);
    }
}
