package com.ssk.haoke.cloud.api.service;

import com.ssk.haoke.cloud.server.pojo.HouseResources;
import com.ssk.haoke.cloud.server.vo.TableResult;

public interface IHouseResourcesService {

    boolean delete(Long id);

    /**
     * @param houseResources
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    boolean save(HouseResources houseResources);
    /**
     * 分页查询房源列表
     * *
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @param houseResources 查询条件
     * @return
     */
    TableResult<HouseResources> queryList(Integer pageNum, Integer pageSize, HouseResources houseResources);

    /**
     * 根据id删除房源
     * @param id
     * @return
     */
    HouseResources queryById(Long id);
    //更新房源
    boolean update(HouseResources houseResources);
}
