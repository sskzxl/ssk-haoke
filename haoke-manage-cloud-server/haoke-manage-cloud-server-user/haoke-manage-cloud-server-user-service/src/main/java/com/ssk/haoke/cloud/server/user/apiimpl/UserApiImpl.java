package com.ssk.haoke.cloud.server.user.apiimpl;

import com.ssk.haoke.cloud.server.user.api.IUserApi;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 服务实现类
*
* @author 代码生成器
*/
@Service("userApi")
public class UserApiImpl implements IUserApi {

    @Resource
    private IUserService userService;

    @Override
    public RestResponse<Long> addUser(UserReqDto addReqDto) {
        return new RestResponse(userService.addUser(addReqDto));
    }

    @Override
    public RestResponse<Void> modifyUser(UserReqDto modifyReqDto) {
        userService.modifyUser(modifyReqDto);
        return RestResponse.VOID;
    }

    @Override
    public RestResponse<Void> removeUser(String ids) {
        userService.removeUser(ids);
        return RestResponse.VOID;
    }
}
