package com.ssk.haoke.cloud.server.apiimpl.query;

import com.ssk.haoke.cloud.server.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.api.query.IUserQueryApi;
import com.ssk.haoke.cloud.server.rest.RestResponse;
import com.ssk.haoke.cloud.server.service.IUserService;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 服务实现类
*
* @author 代码生成器
*/
@Service
public class UserQueryApiImpl implements IUserQueryApi {

    @Resource
    private IUserService userService;

    @Override
    public RestResponse<UserReqDto> queryById(Long id){
        return new RestResponse(userService.queryById(id));
    }

    @Override
    public RestResponse<PageInfo<UserReqDto>> queryByPage(String filter,
                                                          Integer pageNum,
                                                          Integer pageSize){
        return new RestResponse(userService.queryByPage(filter, pageNum, pageSize));
    }

}
