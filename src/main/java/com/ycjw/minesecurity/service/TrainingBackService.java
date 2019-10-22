package com.ycjw.minesecurity.service;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.TrainingBack;

import java.util.List;

public interface TrainingBackService {
    TrainingBack addTrainingBack(String telephone,String content) throws ExceptionZyc;
    List<TrainingBack> findTrainingBackByTelephone(String telephone) throws ExceptionZyc;
}
