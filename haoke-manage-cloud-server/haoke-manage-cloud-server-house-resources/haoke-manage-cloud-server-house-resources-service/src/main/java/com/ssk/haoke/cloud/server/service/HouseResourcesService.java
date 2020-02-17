package com.ssk.haoke.cloud.server.service;

import com.ssk.haoke.cloud.server.pojo.HouseResources;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import com.ssk.haoke.cloud.server.vo.TableResult;


public interface HouseResourcesService {
    /**
     *
     * @param id
     * @return
     */
    public boolean deleteHouseResources(Long id);
    /**
     * @param houseResources
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    int saveHouseResources(HouseResources houseResources);

    /**
     * 查询房源列表
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<HouseResources> queryHouseResourcesList(Integer page, Integer pageSize);

    /**
     * 根据id查询房源
     * @param id
     * @return
     */
    HouseResources queryHouseResourcesById(Long id);

    /*
    更新房源
     */
    boolean updateHouseResources(HouseResources houseResources);
}