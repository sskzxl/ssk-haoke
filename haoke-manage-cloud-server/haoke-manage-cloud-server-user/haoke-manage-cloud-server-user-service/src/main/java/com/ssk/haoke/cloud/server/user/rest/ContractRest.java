package com.ssk.haoke.cloud.server.user.rest;

import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.api.IContractApi;
import com.ssk.haoke.cloud.server.user.api.dto.request.ContractReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.ContractRespDto;
import com.ssk.haoke.cloud.server.user.api.query.IContractQueryApi;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Rest类
*
* @author 代码生成器
*/
@RestController
@RequestMapping("/v1/contract")
public class ContractRest implements IContractApi,IContractQueryApi {

    @Resource
    private IContractApi contractApi;
    @Resource
    private IContractQueryApi contractQueryApi;

    @Override
    public RestResponse<Long> addContract(@RequestBody ContractReqDto addReqDto) {
        return contractApi.addContract(addReqDto);
    }

    @Override
    public RestResponse<Void> modifyContract(@RequestBody ContractReqDto modifyReqDto) {
        return contractApi.modifyContract(modifyReqDto);
    }

    @Override
    public RestResponse<Void> removeContract(@PathVariable("ids") String ids) {
        return contractApi.removeContract(ids);
    }

    @Override
    public RestResponse<ContractRespDto> queryById(@PathVariable("id") Long id) {
        return contractQueryApi.queryById(id);
    }

    @Override
    public RestResponse<PageInfo<ContractRespDto>> queryByPage(@RequestParam("filter") String filter,
                                                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return contractQueryApi.queryByPage(filter, pageNum, pageSize);
    }


}