package com.ssk.haoke.cloud.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssk.haoke.cloud.api.mapper.UserMapper;
import com.ssk.haoke.cloud.api.util.impl.RedisTokenManager;
import com.ssk.haoke.cloud.api.vo.user.User;
import com.ssk.haoke.cloud.api.vo.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserLoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTokenManager tokenManager;
    public UserResponse checkLogin(User user){
        if(user == null){
            //用户不存在
            return UserResponse.getFail(2);
        }
        QueryWrapper queryWrapper = new QueryWrapper(new User());
        HashMap<String, String> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        queryWrapper.allEq(map);

        Integer count = userMapper.selectCount(queryWrapper);
        if(count>0){
            String token = tokenManager.getToken(user.getUsername());
            return UserResponse.getSuccess("ssk",token);
        }
        //密码错误
        return UserResponse.getFail(1);

    }




}
