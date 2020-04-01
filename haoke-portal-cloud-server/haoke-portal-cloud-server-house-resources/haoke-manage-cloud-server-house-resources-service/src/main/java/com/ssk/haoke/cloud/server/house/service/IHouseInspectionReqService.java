package com.ssk.haoke.cloud.server.house.service;

import com.ssk.haoke.cloud.server.house.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;

/**
* 看房请求表服务接口
*
* @author 代码生成器
*/
public interface IHouseInspectionReqService {

    /**
    * 新增看房请求
    *
    * @param addReqDto 看房请求表请求对象
    * @return 新增看房请求表ID
    */
    Long addHouseInspectionReq(HouseInspectionReqReqDto addReqDto);

    /**
    * 修改看房请求表
    *
    * @param modifyReqDto 看房请求表请求对象
    */
    void modifyHouseInspectionReq(HouseInspectionReqReqDto modifyReqDto);

    /**
    * 删除看房请求表
    *
    * @param ids        看房请求表删除数据ID
    */
    void removeHouseInspectionReq(String ids);

    /**
    * 根据id查询看房请求表
    *
    * @param id 看房请求表id
    * @return   看房请求表数据
    */
    HouseInspectionRespDto getById(Long id);

    /**
    * 看房请求表分页数据
    *
    * @param filter   看房请求表查询条件
    * @param pageNum  当前页
    * @param pageSize 页大小
    * @return 看房请求表分页数据
    */
    PageInfo<HouseInspectionRespDto> queryByPage(String filter,
                                                 Integer pageNum,
                                                 Integer pageSize);

}
