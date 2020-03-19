package com.ssk.haoke.cloud.portal.api.controller;

import com.ssk.haoke.cloud.portal.api.service.impl.UserLoginService;
import com.ssk.haoke.cloud.server.common.ServiceResponse;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ServiceResponse<UserRespDto> login(@RequestBody UserReqDto userReqDto, HttpSession session){
        ServiceResponse response = userLoginService.login(userReqDto.getUsername(), userReqDto.getPassword());
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
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "登出",notes = "logout")
    public ServiceResponse logout(HttpServletRequest request, HttpServletResponse response){
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.name());
            return ServiceResponse.createByErrorCodeMessage(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.name());
        }
        return userLoginService.logout(authorization,response);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息",notes = "info")
    public ServiceResponse<UserRespDto> getInfo(HttpServletRequest request, HttpServletResponse response){
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.name());
            return ServiceResponse.createByErrorCodeMessage(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.name());
        }
        return userLoginService.getInfo(authorization,response);
    }
}
