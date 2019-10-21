package com.ycjw.minesecurity.service.impl;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import com.ycjw.minesecurity.model.LearnRecord;
import com.ycjw.minesecurity.model.LearnResource;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.repository.LearnRecordRepository;
import com.ycjw.minesecurity.service.LearnRecordService;
import com.ycjw.minesecurity.service.LearnResourceService;
import com.ycjw.minesecurity.utils.DateUtil;
import com.ycjw.minesecurity.utils.KeyUtil;
import com.ycjw.minesecurity.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class LearnRecordServiceImpl implements LearnRecordService {

    @Autowired
    private LearnRecordRepository learnRecordRepository;

    /**
     * 创建一个学习记录
     * @param phoneNum
     * @param timeLong
     * @param resourceId
     * @return
     * @throws Exception
     */
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

    /**\
     *查询用户全部学习记录
     * @param phone
     * @return
     * @throws Exception
     */
    @Override
    public Response findAllByUserPhone(String phone) throws Exception{
        List<LearnRecord> result=findSomeResourceIdByPhone(phone,100,0);
        List<LearnRecord> ans=result;
        int i=1;
        while (result.size()>=100){
            result=findSomeResourceIdByPhone(phone,100,i);
            ans.addAll(result);
            i++;
        }
        return new Response("成功",ans);
    }

    /**
     * 查询用户最近的两条学习记录
     * @param phone
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<LearnRecord>  findSomeResourceIdByPhone(String phone,int size,int pageNum) throws Exception{
        Sort sort=new Sort(Sort.Direction.DESC,"learnDate");
        Pageable pageable=PageRequest.of(pageNum,size,sort);
        Page<LearnRecord> page=learnRecordRepository.findByPhoneNum(phone,pageable);
        return page.getContent();
    }

    /**
     * 统计用户每天的学习时间
     * @param phoneNum
     * @return
     * @throws Exception
     */
    @Override
    public Response countLearnRecord(String phoneNum) throws Exception {
        List<LearnRecord> recordList=learnRecordRepository.findAllByPhoneNum(phoneNum);
        if (recordList==null){
            return new Response("暂时没有学习记录",null);
        }
        Iterator<LearnRecord> iterator=recordList.iterator();
        HashMap<Long,Long> result=new HashMap<Long,Long>();
        while (iterator.hasNext()) {
            LearnRecord learnRecord=iterator.next();
            Long key=DateUtil.convert2Day(learnRecord.getLearnDate()).getTime();
            if (result.containsKey(key)){
                result.put(key,result.get(key)+learnRecord.getTimeLong());
            }
            else {
                result.put(key,Long.valueOf(learnRecord.getTimeLong()));
            }
        }
        return new Response("成功",result);
    }

    /**
     * 查询某个用户的学习时间之和
     * @param phone
     * @return
     */
     @Override
     public long  getTimeSum(String phone) throws Exception{
        return (learnRecordRepository.getLearnTimeSum(phone)).stream()
                                                                   .reduce(0L,(a,b)->a+b);
     }

}
