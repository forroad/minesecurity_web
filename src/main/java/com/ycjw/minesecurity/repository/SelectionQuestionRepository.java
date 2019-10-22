package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.SelectionQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * 查询数个id不在list中的题目
     * @param pageable
     * @return
     */
  Page<SelectionQuestion> findByQuestionIdNotIn(List<String> list,Pageable pageable);

    /**
     * 查询列表中所有id对应的题目
     * @param questionIdList
     * @return
     */
  List<SelectionQuestion> findAllByQuestionIdIn(List<String> questionIdList);

}
