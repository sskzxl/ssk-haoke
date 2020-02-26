package com.ssk.haoke.cloud.api.controller;

import com.ssk.haoke.cloud.api.service.impl.UserLoginService;
import com.ssk.haoke.cloud.api.vo.user.User;
import com.ssk.haoke.cloud.api.vo.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin
@RequestMapping("users/login")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @ResponseBody
    @PostMapping
    public UserResponse checkLogin(@RequestBody User user, HttpServletRequest request){
        UserResponse userResponse = userLoginService.checkLogin(user);
        System.out.println(userResponse);
        return userResponse;
    }
}
