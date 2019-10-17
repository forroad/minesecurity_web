package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.SelectionQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectionQuestionRepository extends JpaRepository<SelectionQuestion,String> {

    /**
     * 查询id不在completedIdList中的所有选择题
     * @param completedIdList
     * @return
     */
  List<SelectionQuestion>  findAllByQuestionIdNotIn(List<String> completedIdList);

}
