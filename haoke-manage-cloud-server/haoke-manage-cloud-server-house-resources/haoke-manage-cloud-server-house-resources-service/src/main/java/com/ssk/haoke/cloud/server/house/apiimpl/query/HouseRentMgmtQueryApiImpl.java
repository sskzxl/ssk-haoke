package com.ssk.haoke.cloud.server.house.apiimpl.query;

import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtDetailRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseRentMgmtQueryApi;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.service.IHouseRentMgmtService;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 租房管理表服务实现类
*
* @author 代码生成器
*/
@Service("houseRentMgmtQueryApi")
public class HouseRentMgmtQueryApiImpl implements IHouseRentMgmtQueryApi{

    @Resource
    private IHouseRentMgmtService rentManagementService;

    @Override
    public RestResponse<HouseRentMgmtDetailRespDto> queryById(Long id){
        return new RestResponse<>(rentManagementService.getById(id));
    }

    @Override
    public RestResponse<PageInfo<HouseRentMgmtRespDto>> queryByPage(String filter,
                                                      Integer pageNum,
                                                      Integer pageSize){
        return new RestResponse<>(rentManagementService.queryByPage(filter, pageNum, pageSize));
    }

}
