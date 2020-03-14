package com.ssk.haoke.cloud.portal.api.controller;

import com.ssk.haoke.cloud.portal.api.service.impl.UserLoginService;
import com.ssk.haoke.cloud.server.common.ServiceResponse;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("users")
@Api(tags = "用户登陆")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

//    @PostMapping()
//    public UserResponse checkLogin(@RequestBody User user, HttpServletRequest request){
////        UserResponse userResponse = userLoginService.checkLogin(user);
////        System.out.println(userResponse);
////        return userResponse;
//    }

    @PostMapping("/login")
    @ApiOperation(value = "登陆",notes = "login")
    public ServiceResponse<UserRespDto> login(@RequestParam ("username")String username, @RequestParam("password") String password, HttpSession session){
        ServiceResponse response = userLoginService.login(username, password);
        return response;
    }

    /**
     * 注册操作
     * @param userReqDto
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册",notes = "register")
    public ServiceResponse<String> register(@RequestBody UserReqDto userReqDto){
        return userLoginService.register(userReqDto);
    }
    /**
     * 登出操作
     * @param token
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "登出",notes = "logout")
    public void logout(String token){
        userLoginService.logout(token);
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @PostMapping("/info")
    @ApiOperation(value = "登出",notes = "logout")
    public ServiceResponse<UserRespDto> getInfo(String token){
        return userLoginService.getInfo(token);
    }
}
