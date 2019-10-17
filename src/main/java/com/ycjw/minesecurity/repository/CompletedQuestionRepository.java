package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.CompletedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedQuestionRepository extends JpaRepository<CompletedQuestion,String> {
    /*或者方法2
       @Query(value = "select * from product_category where category_type in (:spIds) ", nativeQuery = true)
        List<ProductCategory> getProductCategoryList(@Param("spIds") List<Integer> spIds);
         */
    /*@Query(value = "select question_id from question_completed where phone_num=")
    List<String> findAllIdCompleted(String phone);*/

    /**
     * 查找某个用户刷完的所有题
     * @param phoneNum
     * @return
     */
    List<CompletedQuestion> findAllByPhoneNum(String phoneNum);

}
