package com.ssk.haoke.cloud.manage.api.service.impl;

import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.api.IContractApi;
import com.ssk.haoke.cloud.server.user.api.dto.request.ContractReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.ContractRespDto;
import com.ssk.haoke.cloud.server.user.api.query.IContractQueryApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("houseContractService")
public class HouseContractServiceImpl {
    
    @Resource
    private IContractQueryApi contractQueryApi;
    @Resource
    private IContractApi contractApi;
    //删除合同
    public RestResponse<Void> delete(String ids){
        return this.contractApi.removeContract(ids);
    }
    //增加房源
    public RestResponse<Long> save(ContractReqDto contractReqDto) {
        RestResponse<Long> result = this.contractApi.addContract(contractReqDto);
        return result;
    }
    //查询合同列表
    public RestResponse<PageInfo<ContractRespDto>> queryList(Integer pageNum, Integer pageSize, String filter) {
        RestResponse<PageInfo<ContractRespDto>> response = this.contractQueryApi.queryByPage(filter, pageNum, pageSize);

        return response;
    }
    /**
     * @param id
     * @return
     */
    public RestResponse<ContractRespDto> queryById(Long id){
        return this.contractQueryApi.queryById(id);
    }
    /*
        更新合同
     */
    public RestResponse<Void> update(ContractReqDto contractReqDto) {
        return this.contractApi.modifyContract(contractReqDto);
    }


}