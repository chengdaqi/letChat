package com.letchat.web.service;


import com.letchat.web.pojo.Users;

/**
 * @author alice
 */
public interface LoginService {

    /**
     * 查询用户
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户
     * **/
    Users queryUser(String userName, String password);

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户
     * **/
    Users queryUserByUserName(String userName);

    /**
     * 注册用户
     * @param userInfo 用户信息
     * **/
    void registerUser(Users userInfo);
}
