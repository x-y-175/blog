package com.hua.service.Impl;

import com.hua.dao.UserDao;
import com.hua.pojo.User;
import com.hua.service.UserService;
import com.hua.util.MD5Utils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(@Param("username") String username,@Param("password") String password) {
        User user = userDao.checkUser(username, MD5Utils.code(password));
        return user;
    }
}
