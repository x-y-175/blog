package com.hua.service;

import com.hua.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
