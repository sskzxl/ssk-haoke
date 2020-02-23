package com.ssk.haoke.cloud.server.service;

import com.ssk.haoke.cloud.server.api.dto.request.HouseRentMgmtReqDto;
import com.ssk.haoke.cloud.server.api.dto.response.HouseRentMgmtRespDto;
import com.ssk.haoke.cloud.server.vo.PageInfo;

/**
* 租房管理表服务接口
*
* @author 代码生成器
*/
public interface IHouseRentMgmtService {

    /**
    * 新增租房管理表
    *
    * @param addReqDto 租房管理表请求对象
    * @return 新增租房管理表ID
    */
    Long addRentManagement(HouseRentMgmtReqDto addReqDto);

    /**
    * 修改租房管理表
    *
    * @param modifyReqDto 租房管理表请求对象
    */
    void modifyRentManagement(HouseRentMgmtReqDto modifyReqDto);

    /**
    * 删除租房管理表
    *
    * @param ids        租房管理表删除数据ID
    */
    void removeRentManagement(String ids);

    /**
    * 根据id查询租房管理表
    *
    * @param id 租房管理表id
    * @return   租房管理表数据
    */
    HouseRentMgmtRespDto getById(Long id);

    /**
    * 租房管理表分页数据
    *
    * @param filter   租房管理表查询条件
    * @param pageNum  当前页
    * @param pageSize 页大小
    * @return 租房管理表分页数据
    */
    PageInfo<HouseRentMgmtRespDto> queryByPage(String filter,
                                               Integer pageNum,
                                               Integer pageSize);

}
