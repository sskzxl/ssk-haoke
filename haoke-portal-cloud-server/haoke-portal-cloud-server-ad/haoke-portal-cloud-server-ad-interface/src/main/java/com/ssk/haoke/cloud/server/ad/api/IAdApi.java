package com.ssk.haoke.cloud.server.ad.api;

import com.ssk.haoke.cloud.server.ad.pojo.Ad;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "haoke-manage-cloud-server-ad",
        path = "/v1/house/ad",
    url = "${ssk-haoke-ad-api:}")
@Api(tags = "好客租房运营前台：广告中心")
public interface IAdApi {
    /**
     * 分页查询广告数据
     *
     * @param type     广告类型
     * @param page     页数
     * @param pageSize 每页显示的数据条数
     * @return
     */
    @GetMapping
    @ApiOperation(value = "分页查询广告" , notes = "分页查询广告")
    PageInfo<Ad> queryAdList(@RequestParam("type") Integer type, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize);
}