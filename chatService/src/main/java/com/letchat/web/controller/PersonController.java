package com.letchat.web.controller;

import com.letchat.common.Response;
import com.letchat.web.pojo.Users;
import com.letchat.web.pojo.vo.UsersVO;
import com.letchat.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关控制器
 *
 * @author yanAn
 * @version 1.0
 * @date 2021-1-22
 **/
@RestController
@RequestMapping("/person")
public class PersonController {

    private final UserService userService;

    public PersonController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/getUserInfo")
    public Response getUserInfo(@RequestBody UsersVO usersVO) {
        if (StringUtils.isNotBlank(usersVO.getUsername())) {
            Users user = userService.findUserByUserName(usersVO.getUsername());
            if (user != null) {
                return Response.success("用户查找成功",user);
            }
            return Response.fail("用户查找为空！");
        }
        return Response.fail("用户名为空！");
    }
}
