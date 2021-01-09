package com.letchat.controller;

import com.letchat.common.Response;
import com.letchat.common.ResponseCode;
import com.letchat.pojo.Users;
import com.letchat.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alice
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("registOrLogin")
    public Response registOrLogin(@RequestBody String userName, @RequestBody String password) {
        if (StringUtils.isNoneBlank(userName, password)) {
            Users user = loginService.queryUser(userName, password);
            if (user == null) {
                //注册逻辑
                boolean result = registerUser(userName, password);
                if (result) {
                    return Response.success("用户注册成功，已登录！");
                }
                return Response.fail("用户注册失败！~");
            } else {
                return Response.success("登录成功！");
            }
        } else {
            return Response.fail(ResponseCode.PARAMETER_ERROR.getCode(), "参数错误，用户名或密码为空！");
        }
    }

    private boolean registerUser(String userName, String password){
        Users user = loginService.queryUserByUserName(userName);
        if(user!=null){
            return false;
        }else{
            // loginService.registerUser();
            return true;
        }
    }

    @PostMapping("registerUser")
    public Response chechUser(String userName, String password) {
        // 判断用户名是否重复
        return null;
    }
}