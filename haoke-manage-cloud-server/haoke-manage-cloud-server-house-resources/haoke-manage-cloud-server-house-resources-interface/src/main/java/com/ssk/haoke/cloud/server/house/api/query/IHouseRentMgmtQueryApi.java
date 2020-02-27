package com.ssk.haoke.cloud.server.house.api.query;

import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtDetailRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtRespDto;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
* 租房管理表服务接口
*
* @author 代码生成器
*/
@Api(tags = "好客租房管理平台：房源中心")
@FeignClient(name = "${haoke.manage.center.resources.api.name:haoke-manage-center-resources}",
        path = "/v1/houserent",
        url = "${haoke.manage.center.resources.api:}")
public interface IHouseRentMgmtQueryApi {

    /**
    * 根据id查询租房管理表
    *
    * @param id 租房管理表id
    * @return   租房管理表数据
    */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询租房管理表", notes = "根据id查询租房管理表")
    RestResponse<HouseRentMgmtDetailRespDto> queryById(@PathVariable("id") Long id);

    /**
    * 租房管理表分页数据
    *
    * @param filter   租房管理表查询条件
    * @param pageNum  当前页
    * @param pageSize 页大小
    * @return 租房管理表分页数据
    */
    @GetMapping("/page")
    @ApiOperation(value = "租房管理表分页数据", notes = "根据filter查询条件查询租房管理表数据，filter=HouseRentMgmtReqDto")
    RestResponse<PageInfo<HouseRentMgmtRespDto>> queryByPage(@RequestParam("filter") String filter,
                                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize);

}
