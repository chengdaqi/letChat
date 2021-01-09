package com.letchat.service;


import com.letchat.pojo.Users;

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
}
