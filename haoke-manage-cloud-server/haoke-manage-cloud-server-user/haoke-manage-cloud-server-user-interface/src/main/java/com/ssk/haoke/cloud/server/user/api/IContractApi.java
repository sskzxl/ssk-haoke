package com.ssk.haoke.cloud.server.user.api;

import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.api.dto.request.ContractReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
* 服务接口
*
* @author 代码生成器
*/
@Api(tags = {"好客租房管理平台：合同服务"})
@FeignClient(name = "${haoke.manage.center.user.api.name:haoke-manage-center-user}",
        path = "/v1/contract", url = "${haoke.manage.center.user.api:}")
public interface IContractApi {

    /**
    * 新增
    *
    * @param addReqDto 请求对象
    * @return 处理结果
    */
    @PostMapping("")
    @ApiOperation(value = "新增", notes = "新增")
    RestResponse<Long> addContract(@RequestBody ContractReqDto addReqDto);

    /**
    * 修改
    *
    * @param modifyReqDto 请求对象
    * @return 处理结果
    */
    @PutMapping("")
    @ApiOperation(value = "修改", notes = "修改")
    RestResponse<Void> modifyContract(@RequestBody ContractReqDto modifyReqDto);

    /**
    * 删除
    *
    * @param ids        删除数据ID
    * @param instanceId 实例ID
    * @return 处理结果
    */
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除", notes = "删除")
    RestResponse<Void> removeContract(@PathVariable("ids") String ids);
}
