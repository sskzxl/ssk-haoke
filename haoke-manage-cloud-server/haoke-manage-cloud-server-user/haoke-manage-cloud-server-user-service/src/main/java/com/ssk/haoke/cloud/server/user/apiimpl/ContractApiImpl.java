package com.ssk.haoke.cloud.server.user.apiimpl;

import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.api.IContractApi;
import com.ssk.haoke.cloud.server.user.api.dto.request.ContractReqDto;
import com.ssk.haoke.cloud.server.user.service.IContractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 服务实现类
*
* @author 代码生成器
*/
@Service("contractApi")
public class ContractApiImpl implements IContractApi {

    @Resource
    private IContractService contractService;

    @Override
    public RestResponse<Long> addContract(ContractReqDto addReqDto) {
        return new RestResponse(contractService.addContract(addReqDto));
    }

    @Override
    public RestResponse<Void> modifyContract(ContractReqDto modifyReqDto) {
        contractService.modifyContract(modifyReqDto);
        return RestResponse.VOID;
    }

    @Override
    public RestResponse<Void> removeContract(String ids) {
        contractService.removeContract(ids);
        return RestResponse.VOID;
    }
}
