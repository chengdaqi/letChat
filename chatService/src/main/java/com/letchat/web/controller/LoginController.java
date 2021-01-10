package com.letchat.web.controller;

import com.letchat.common.Response;
import com.letchat.common.ResponseCode;
import com.letchat.web.pojo.Users;
import com.letchat.web.pojo.vo.UsersVO;
import com.letchat.web.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    public Response registOrLogin(@RequestBody UsersVO userInfo) {
        if (StringUtils.isNoneBlank(userInfo.getUsername(), userInfo.getPassword())) {
            Users user = loginService.queryUser(userInfo.getUsername(), userInfo.getPassword());
            if (user == null) {
                //注册逻辑
                boolean result = registerUser(userInfo.getUsername(), userInfo.getPassword());
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

    private boolean registerUser(String username, String password) {
        Users user = loginService.queryUserByUserName(username);
        if (user != null) {
            return false;
        } else {
            // 注册用户
            loginService.registerUser(username, password);
            return true;
        }
    }
}
