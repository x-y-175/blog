package com.hua.dao;

import com.hua.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User checkUser(@Param("username") String username, @Param("password") String password);
}
