package com.ssk.haoke.cloud.server.apiimpl.query;

import com.ssk.haoke.cloud.server.api.dto.response.HouseRentMgmtRespDto;
import com.ssk.haoke.cloud.server.api.query.IHouseRentMgmtQueryApi;
import com.ssk.haoke.cloud.server.rest.RestResponse;
import com.ssk.haoke.cloud.server.service.IHouseRentMgmtService;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 租房管理表服务实现类
*
* @author 代码生成器
*/
@Service
public class HouseRentMgmtQueryApiImpl implements IHouseRentMgmtQueryApi{

    @Resource
    private IHouseRentMgmtService rentManagementService;

    @Override
    public RestResponse<HouseRentMgmtRespDto> queryById(Long id){
        return new RestResponse<>(rentManagementService.getById(id));
    }

    @Override
    public RestResponse<PageInfo<HouseRentMgmtRespDto>> queryByPage(String filter,
                                                      Integer pageNum,
                                                      Integer pageSize){
        return new RestResponse<>(rentManagementService.queryByPage(filter, pageNum, pageSize));
    }

}
