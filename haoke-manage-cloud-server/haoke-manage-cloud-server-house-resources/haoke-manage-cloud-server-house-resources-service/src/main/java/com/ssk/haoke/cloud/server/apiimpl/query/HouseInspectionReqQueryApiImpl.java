package com.ssk.haoke.cloud.server.apiimpl.query;

import com.ssk.haoke.cloud.server.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.api.query.IHouseInspectionReqQueryApi;
import com.ssk.haoke.cloud.server.rest.RestResponse;
import com.ssk.haoke.cloud.server.service.IHouseInspectionReqService;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 看房请求表服务实现类
*
* @author 代码生成器
*/
@Service("houseInspectionReqQueryApi")
public class HouseInspectionReqQueryApiImpl implements IHouseInspectionReqQueryApi {

    @Resource
    private IHouseInspectionReqService houseInspectionReqService;

    @Override
    public RestResponse<HouseInspectionRespDto> queryById(Long id){
        return new RestResponse<>(houseInspectionReqService.getById(id));
    }

    @Override
    public RestResponse<PageInfo<HouseInspectionRespDto>> queryByPage(String filter,
                                                                        Integer pageNum,
                                                                        Integer pageSize){
        return new RestResponse<>(houseInspectionReqService.queryByPage(filter, pageNum, pageSize));
    }

}
