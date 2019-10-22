package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.LearnRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LearnRecordRepository extends JpaRepository<LearnRecord,String> {

    //通过电话查找
    List<LearnRecord> findAllByPhoneNum(String phoneNum);

    /**
     * 查询用户最近的几个学习记录
     * @param phoneNUm
     * @param pageable
     * @return
     */
    Page<LearnRecord> findByPhoneNum(String phoneNUm,Pageable pageable);

    //查询某个用户的所学习时间之和
    @Query(value = "select time_long from learn_record where phone_num=:phone",nativeQuery = true)
    List<Long> getLearnTimeSum(@Param("phone")String phone);


    /**
     * 查询所有，降序
     * @param phoneNum
     * @returnu
     */
     //List<LearnRecord> findAllByPhoneNumAndOrderByLearnDateDesc(String phoneNum);

    List<LearnRecord> findByLearnDateBetween(Date start_time,Date end_time);
}
