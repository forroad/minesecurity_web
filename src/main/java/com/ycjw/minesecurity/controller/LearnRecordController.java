package com.ycjw.minesecurity.controller;

import com.ycjw.minesecurity.enums.ResultStateEnum;
import com.ycjw.minesecurity.model.LearnRecord;
import com.ycjw.minesecurity.model.Resource;
import com.ycjw.minesecurity.model.response.Response;
import com.ycjw.minesecurity.repository.ResourceDao;
import com.ycjw.minesecurity.service.LearnRecordService;
import com.ycjw.minesecurity.utils.LogUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/learn_record")
public class LearnRecordController {

    @Autowired
    private LearnRecordService learnRecordService;
    @Autowired
    private ResourceDao resourceDao;

    @ApiOperation("添加学习记录")
    @PostMapping("/create_learn_record")
    public Response createLearnRecord(@RequestParam(required = true,value = "phoneNum") String phoneNum,
                               @RequestParam(required = true,value = "timeLong") int timeLong,
                               @RequestParam(required = true,value = "resourceId") String resourceId) throws Exception{
        return learnRecordService.createLearnRecord(phoneNum,timeLong,resourceId);
    }

    @ApiOperation("统计学习记录")
    @GetMapping(value = "/count_learn_record")
    public Response countLearnRecord(@RequestParam(required = true,value = "phoneNum") String phoneNum) throws Exception{
        return learnRecordService.countLearnRecord(phoneNum);
    }

    @ApiOperation("查询某个用户学习总时间")
    @GetMapping(value = "/get_timelong_sum")
    public Response getTimeLongSum(@RequestParam(required = true,value = "phone")String phone)throws Exception{
        return new Response(ResultStateEnum.RESULT_STATE_SUCCESS.getMessage()
                                                    ,learnRecordService.getTimeSum(phone));
    }


    @ApiOperation("【查询一定数量的用户学习记录】")
    @PostMapping("/find_some_record")
    public Response findSomeRecord(@RequestParam(required = true,value = "phone")String phone,
                                @RequestParam(required = false,value = "size",defaultValue = "2")int size) throws Exception{
        if (phone==null || size<0){
            LogUtil.logger.error("【查询用户学习记录】--失败---参数错误：phone={"+
                                  phone+"},size={"+size+"}");
            return new Response("失败",null);
        }
        List<LearnRecord> learnRecordList=null;
        List<Resource> resources = new ArrayList<>();
        try{
            learnRecordList=learnRecordService.findSomeResourceIdByPhone(phone,size,0);
            for(int i = 0;i < learnRecordList.size();i++){
                Resource resource =resourceDao.findByResourceId(learnRecordList.get(i).getResourceId());
                if(resource != null){
                    resources.add(resource);
                }
            }
        }
        catch (Exception e){
            LogUtil.logger.error("【查询用户两个学习记录】--失败---系统错误：phone={"+
                    phone+"},size={"+size+"}"+"\n"+
                    e.getMessage());
            return new Response("失败",null);
        }
        return  new Response("成功",resources);
    }

}
