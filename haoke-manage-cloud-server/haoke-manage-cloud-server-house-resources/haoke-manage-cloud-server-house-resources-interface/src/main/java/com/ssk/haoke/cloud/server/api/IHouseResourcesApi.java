package com.ssk.haoke.cloud.server.api;
import com.ssk.haoke.cloud.server.pojo.HouseResources;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import com.ssk.haoke.cloud.server.vo.TableResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "haoke-manage-cloud-server-house-resources",
        path = "/v1/house/resources",
    url = "${ssk-haoke-houseResources-api:}")
@Api(tags = "好客租房管理平台：房源中心")
public interface IHouseResourcesApi {
    @ApiOperation(value = "删除房源",notes = "删除房源")
    @DeleteMapping("{id}")
    boolean deleteHouseResource(@PathVariable("id") Long id);

    /**
     * @param houseResources
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    @PostMapping
    @ResponseBody
    @ApiOperation(value = "新增房源",notes = "新增房源")
    int saveHouseResources(@RequestBody HouseResources houseResources);
    /**
    * 分页查询房源列表
    * *
    * @param page 当前页
    * @param pageSize 页面大小
    * @return
    */
    @GetMapping(produces = {"application/json"})
    @ApiOperation(value = "分页查询房源",notes = "分页查询房源")
    PageInfo<HouseResources> queryHouseResourcesList(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize);

    /**
     * 根据id删除房源
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ResponseBody
    @ApiOperation(value = "根据id查询房源",notes = "根据id查询房源")
    HouseResources queryHouseResourcesById(@PathVariable("id") Long id);
    //更新房源
    @PutMapping(produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新房源",notes = "更新房源")
    boolean updateHouseResources(@RequestBody HouseResources houseResources);
}

