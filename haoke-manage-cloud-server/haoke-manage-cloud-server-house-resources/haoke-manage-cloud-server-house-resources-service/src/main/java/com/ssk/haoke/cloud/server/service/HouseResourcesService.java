package com.ssk.haoke.cloud.server.service;

import com.ssk.haoke.cloud.server.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.vo.PageInfo;


public interface HouseResourcesService {
    /**
     *
     * @param id
     * @return
     */
    public boolean deleteHouseResources(Long id);
    /**
     * @param houseResourcesReqDto
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    int saveHouseResources(HouseResourcesReqDto houseResourcesReqDto);

    /**
     * 查询房源列表
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<HouseResourcesRespDto> queryHouseResourcesList(String filter,Integer page, Integer pageSize);

    /**
     * 根据id查询房源
     * @param id
     * @return
     */
    HouseResourcesRespDto queryHouseResourcesById(Long id);

    /*
    更新房源
     */
    boolean updateHouseResources(HouseResourcesReqDto houseResourcesReqDto);
}