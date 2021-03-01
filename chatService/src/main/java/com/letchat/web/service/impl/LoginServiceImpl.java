package com.letchat.web.service.impl;

import com.letchat.web.mapper.UsersMapper;
import com.letchat.web.pojo.Users;
import com.letchat.web.service.LoginService;
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
    public Users queryUser(String letchatNum, String password) {
        // password 进行加密
        String shaEncryption = EncryptionUtils.getShaEncryption(password, letchatNum);
        Users users = Users.builder().letchatNum(letchatNum).password(shaEncryption).build();
        return usersMapper.selectOne(users);
    }

    @Override
    public Users queryUserByUserName(String letchatNum) {
        Users user = Users.builder().letchatNum(letchatNum).build();
        return usersMapper.selectOne(user);
    }

    @Override
    public void registerUser(Users userInfo) {
        String shaEncryption = EncryptionUtils.getShaEncryption(userInfo.getPassword(), userInfo.getLetchatNum());
        userInfo.setPassword(shaEncryption);
        usersMapper.insert(userInfo);
    }
}
