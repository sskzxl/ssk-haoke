package com.ssk.haoke.cloud.server.user.rest;

import com.ssk.haoke.cloud.server.user.api.IUserApi;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.query.IUserQueryApi;
import com.ssk.haoke.cloud.server.eo.PageInfo;
import com.ssk.haoke.cloud.server.rest.RestResponse;
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
    public RestResponse<UserReqDto> queryById(@PathVariable("id") Long id) {
        return userQueryApi.queryById(id);
    }

    @Override
    public RestResponse<PageInfo<UserReqDto>> queryByPage(@RequestParam("filter") String filter,
                                                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return userQueryApi.queryByPage(filter, pageNum, pageSize);
    }


}
