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
    public Users queryUser(String username, String password) {
        // password 进行加密
        String shaEncryption = EncryptionUtils.getShaEncryption(password, username);
        Users users = Users.builder().username(username).password(shaEncryption).build();
        return usersMapper.selectOne(users);
    }

    @Override
    public Users queryUserByUserName(String username) {
        Users user = Users.builder().username(username).build();
        return usersMapper.selectOne(user);
    }

    @Override
    public void registerUser(String username, String password) {
        String shaEncryption = EncryptionUtils.getShaEncryption(password, username);
        Users user = Users.builder().username(username).password(shaEncryption).build();
        usersMapper.insert(user);
    }
}
