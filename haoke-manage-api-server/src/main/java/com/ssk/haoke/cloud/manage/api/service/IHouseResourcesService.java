package com.ssk.haoke.cloud.manage.api.service;

import com.ssk.haoke.cloud.server.house.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;

public interface IHouseResourcesService {

    RestResponse<Boolean> delete(Long id);

    /**
     * @param houseResourcesReqDto
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    RestResponse<Boolean> save(HouseResourcesReqDto houseResourcesReqDto);
    /**
     * 分页查询房源列表
     * *
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @param filter 查询条件
     * @return
     */
    RestResponse<PageInfo<HouseResourcesRespDto>> queryList(Integer pageNum, Integer pageSize, String filter);

    /**
     * 根据id删除房源
     * @param id
     * @return
     */
    RestResponse<HouseResourcesRespDto> queryById(Long id);
    //更新房源
    RestResponse<Boolean> update(HouseResourcesReqDto houseResourcesReqDto);
}
