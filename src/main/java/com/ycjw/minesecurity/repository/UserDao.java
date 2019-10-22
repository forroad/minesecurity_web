package com.ycjw.minesecurity.repository;

import com.ycjw.minesecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    User findByPhoneNum(String phoneNum);

}
