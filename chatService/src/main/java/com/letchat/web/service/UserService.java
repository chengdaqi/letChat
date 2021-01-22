package com.letchat.web.service;

import com.letchat.web.pojo.Users;

/**
 * @author yanAn
 * @version 1.0
 * @description
 * @date
 **/
public interface UserService {

    /**
     * 通过用户名查找用户信息
     *
     * @param username 用户名
     * @return 查找到的用户
     **/
    Users findUserByUserName(String username);
}
