package com.ssk.haoke.cloud.server.user.api.query;

import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.api.dto.response.ContractRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
* 服务接口
*
* @author 代码生成器
*/
@Api(tags = {"好客租房管理平台：合同服务"})
@FeignClient(name = "${haoke.manage.center.user.api.name:haoke-manage-center-user}",
        path = "/v1/contract", url = "${haoke.manage.center.user.api:}")
public interface IContractQueryApi {

    /**
    * 根据id查询
    *
    * @param id id
    * @return   数据
    */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    RestResponse<ContractRespDto> queryById(@PathVariable("id") Long id);

    /**
    * 分页数据
    *
    * @param filter   查询条件
    * @param pageNum  当前页
    * @param pageSize 页大小
    * @return 分页数据
    */
    @GetMapping("/page")
    @ApiOperation(value = "分页数据", notes = "根据filter查询条件查询数据，filter=ContractReqDto")
    RestResponse<PageInfo<ContractRespDto>> queryByPage(@RequestParam("filter") String filter,
                                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize);

}
