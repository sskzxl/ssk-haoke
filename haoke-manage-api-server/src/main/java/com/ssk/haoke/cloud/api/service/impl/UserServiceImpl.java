package com.ssk.haoke.cloud.api.service.impl;

import com.ssk.haoke.cloud.api.service.IUserService;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.user.api.IUserApi;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import com.ssk.haoke.cloud.server.user.api.query.IUserQueryApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserApi userApi;
    @Resource
    private IUserQueryApi userQueryApi;
    @Override
    public Long addUser(UserReqDto addReqDto) {
        return userApi.addUser(addReqDto).getData();
    }

    @Override
    public void modifyUser(UserReqDto modifyReqDto) {
        userApi.modifyUser(modifyReqDto);
    }

    @Override
    public void removeUser(String ids) {
        userApi.removeUser(ids);
    }

    @Override
    public UserRespDto queryById(Long id) {
        return userQueryApi.queryById(id).getData();
    }

    @Override
    public PageInfo<UserRespDto> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        return userQueryApi.queryByPage(filter, pageNum, pageSize).getData();
    }
}
