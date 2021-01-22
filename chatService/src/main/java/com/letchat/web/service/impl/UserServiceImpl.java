package com.letchat.web.service.impl;

import com.letchat.web.mapper.UsersMapper;
import com.letchat.web.pojo.Users;
import com.letchat.web.service.UserService;
import org.springframework.stereotype.Component;

/**
 * 用户相关服务
 *
 * @author yanAn
 * @version 1.0
 * @date 2021-1-22
 **/
@Component
public class UserServiceImpl implements UserService {

    private final UsersMapper usersMapper;

    public UserServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public Users findUserByUserName(String username) {
        Users user = Users.builder().username(username).build();
        return usersMapper.selectOne(user);
    }
}
