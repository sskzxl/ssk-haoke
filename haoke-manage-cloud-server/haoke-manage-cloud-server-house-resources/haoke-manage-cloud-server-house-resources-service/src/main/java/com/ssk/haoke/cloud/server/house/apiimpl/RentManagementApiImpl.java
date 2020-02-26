package com.ssk.haoke.cloud.server.house.apiimpl;

import com.ssk.haoke.cloud.server.house.api.IHouseRentMgmtApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseRentMgmtReqDto;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.service.IHouseRentMgmtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 租房管理表服务实现类
*
* @author 代码生成器
*/
@Service("houseRentMgmtApi")
public class RentManagementApiImpl implements IHouseRentMgmtApi {

    @Resource
    private IHouseRentMgmtService houseRentMgmtService;

    @Override
    public RestResponse<Long> addRentManagement(HouseRentMgmtReqDto addReqDto) {
        return new RestResponse(houseRentMgmtService.addRentManagement(addReqDto));
    }

    @Override
    public RestResponse<Void> modifyRentManagement(HouseRentMgmtReqDto modifyReqDto) {
        houseRentMgmtService.modifyRentManagement(modifyReqDto);
        return RestResponse.VOID;
    }

    @Override
    public RestResponse<Void> removeRentManagement(String ids) {
        houseRentMgmtService.removeRentManagement(ids);
        return RestResponse.VOID;
    }
}
