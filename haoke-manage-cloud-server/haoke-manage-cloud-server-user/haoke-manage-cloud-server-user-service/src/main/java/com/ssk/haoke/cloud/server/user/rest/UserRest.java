package com.ssk.haoke.cloud.server.user.rest;

import com.ssk.haoke.cloud.server.common.ServiceResponse;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.api.IUserApi;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import com.ssk.haoke.cloud.server.user.api.query.IUserQueryApi;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/user")
public class UserRest implements IUserApi,IUserQueryApi {

    @Resource
    private IUserApi userApi;
    @Resource
    private IUserQueryApi userQueryApi;

    @Override
    public RestResponse<Long> addUser(@RequestBody UserReqDto addReqDto) {
        return userApi.addUser(addReqDto);
    }

    @Override
    public RestResponse<Void> modifyUser(@RequestBody UserReqDto modifyReqDto) {
        return userApi.modifyUser(modifyReqDto);
    }

    @Override
    public RestResponse<Void> removeUser(@PathVariable("ids") String ids) {
        return userApi.removeUser(ids);
    }

    @Override
    public ServiceResponse login(@RequestParam("username") String username, @RequestParam("password")String password) {
        return userApi.login(username,password);
    }

    @Override
    public ServiceResponse<String> register(@RequestBody UserReqDto userReqDto) {
        return userApi.register(userReqDto);
    }

    @Override
    public RestResponse<UserRespDto> queryById(@PathVariable("id") Long id) {
        return userQueryApi.queryById(id);
    }

    @Override
    public RestResponse<PageInfo<UserRespDto>> queryByPage(@RequestParam("filter") String filter,
                                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return userQueryApi.queryByPage(filter, pageNum, pageSize);
    }


}
