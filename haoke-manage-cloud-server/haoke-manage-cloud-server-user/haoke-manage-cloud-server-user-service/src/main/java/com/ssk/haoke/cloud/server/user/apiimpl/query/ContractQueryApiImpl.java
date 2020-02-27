package com.ssk.haoke.cloud.server.user.apiimpl.query;

import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.api.dto.response.ContractRespDto;
import com.ssk.haoke.cloud.server.user.api.query.IContractQueryApi;
import com.ssk.haoke.cloud.server.user.service.IContractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 服务实现类
*
* @author 代码生成器
*/
@Service("contractQueryApi")
public class ContractQueryApiImpl implements IContractQueryApi {

    @Resource
    private IContractService contractService;

    @Override
    public RestResponse<ContractRespDto> queryById(Long id){
        return new RestResponse<>(contractService.getById(id));
    }

    @Override
    public RestResponse<PageInfo<ContractRespDto>> queryByPage(String filter,
                                                               Integer pageNum,
                                                               Integer pageSize){
        return new RestResponse(contractService.queryByPage(filter, pageNum, pageSize));
    }

}
