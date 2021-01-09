package com.letchat.service.impl;

import com.letchat.mapper.UsersMapper;
import com.letchat.pojo.Users;
import com.letchat.service.LoginService;
import com.letchat.utils.EncryptionUtils;
import org.springframework.stereotype.Service;

/**
 * @author alice
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UsersMapper usersMapper;

    public LoginServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }


    @Override
    public Users queryUser(String userName, String password) {
        // password 进行加密
        String shaEncryption = EncryptionUtils.getShaEncryption(password, userName);
        Users users = Users.builder().username(userName).password(shaEncryption).build();
        return usersMapper.selectOne(users);
    }

    @Override
    public Users queryUserByUserName(String userName) {
        Users user = Users.builder().username(userName).build();
        return usersMapper.selectOne(user);
    }
}
