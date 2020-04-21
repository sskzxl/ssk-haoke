package com.ssk.haoke.cloud.portal.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssk.haoke.cloud.portal.api.mapper.UserMapper;
import com.ssk.haoke.cloud.portal.api.util.impl.RedisTokenManager;
import com.ssk.haoke.cloud.server.common.ServiceResponse;
import com.ssk.haoke.cloud.server.user.api.IUserApi;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Service("userLoginService")
public class UserLoginService {
    @Autowired
    private UserMapper userMapper;
    @Resource
    private IUserApi userApi;
    @Autowired
    private RedisTokenManager tokenManager;
    @Autowired
    ObjectMapper objectMapper;
//    public UserResponse checkLogin(User user){
//        if(user == null){
//            //用户不存在
//            return UserResponse.getFail(2);
//        }
//        QueryWrapper queryWrapper = new QueryWrapper(new User());
//        HashMap<String, String> map = new HashMap<>();
//        map.put("username",user.getUsername());
//        map.put("password",user.getPassword());
//        queryWrapper.allEq(map);
//
//        Integer count = userMapper.selectCount(queryWrapper);
//        if(count>0){
//            String token = tokenManager.getToken(user.getUsername());
//            return UserResponse.getSuccess("ssk",token);
//        }
//        //密码错误
//        return UserResponse.getFail(1);

//    }

    public ServiceResponse login(String username, String password){
        //调用用户微服务判断登陆状态
        ServiceResponse<UserRespDto> login = userApi.login(username, password);
        if (login.isSuccess()){
            //登陆成功，生产token返回给前端
            String token = tokenManager.getToken(login.getData());
            return ServiceResponse.createBySuccess(login.getResultMsg(),token);
        }
        return login;
    }
    public ServiceResponse register(UserReqDto userReqDto){
        return userApi.register(userReqDto);
    }

    public ServiceResponse logout(String token,HttpServletResponse response){
        if (tokenManager.loginOff(token)){
            return ServiceResponse.createBySuccessResultMsg("退出登陆成功");
        }else {
            response.setStatus(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN.name());
            return ServiceResponse.createByErrorCodeMessage(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN.name());
        }
    }

    public ServiceResponse getInfo(String token , HttpServletResponse response){
        String infoByToken = tokenManager.getUserInfoByToken(token);
        if (StringUtils.isNotEmpty(infoByToken)){
            try {
//                ParserConfig parserConfig = new ParserConfig();
//                parserConfig.addDeny("created");

                UserRespDto userRespDto = JSONObject.parseObject(infoByToken, UserRespDto.class);
//                UserRespDto userRespDto = objectMapper.readValue(infoByToken, UserRespDto.class);
                return ServiceResponse.createBySuccess("获取当前登陆用户成功",userRespDto);
            } catch (Exception e) {
                e.printStackTrace();
                return ServiceResponse.createByError();
            }
        }else {
            response.setStatus(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN.name());
            return ServiceResponse.createByErrorCodeMessage(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN.name());
        }
    }

}
