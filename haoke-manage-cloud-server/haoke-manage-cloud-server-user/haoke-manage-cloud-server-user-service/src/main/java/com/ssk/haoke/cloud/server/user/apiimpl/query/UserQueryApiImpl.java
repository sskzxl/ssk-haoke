package com.ssk.haoke.cloud.server.user.apiimpl.query;

import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.query.IUserQueryApi;
import com.ssk.haoke.cloud.server.eo.PageInfo;
import com.ssk.haoke.cloud.server.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 服务实现类
*
* @author 代码生成器
*/
@Service("userQueryApi")
public class UserQueryApiImpl implements IUserQueryApi {

    @Resource
    private IUserService userService;

    @Override
    public RestResponse<UserReqDto> queryById(Long id){
        return new RestResponse(userService.getById(id));
    }

    @Override
    public RestResponse<PageInfo<UserReqDto>> queryByPage(String filter,
                                                          Integer pageNum,
                                                          Integer pageSize){
        return new RestResponse(userService.queryByPage(filter, pageNum, pageSize));
    }

}
