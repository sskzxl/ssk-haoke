package com.ssk.haoke.cloud.server.user.service;

import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.user.api.dto.request.ContractReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.ContractRespDto;

/**
* 服务接口
*
* @author 代码生成器
*/
public interface IContractService {

    /**
    * 新增
    *
    * @param addReqDto 请求对象
    * @return 新增ID
    */
    Long addContract(ContractReqDto addReqDto);

    /**
    * 修改
    *
    * @param modifyReqDto 请求对象
    */
    void modifyContract(ContractReqDto modifyReqDto);

    /**
    * 删除
    *
    * @param ids        删除数据ID
    */
    void removeContract(String ids);

    /**
    * 根据id查询
    *
    * @param id id
    * @return   数据
    */
    ContractRespDto getById(Long id);

    /**
    * 分页数据
    *
    * @param filter   查询条件
    * @param pageNum  当前页
    * @param pageSize 页大小
    * @return 分页数据
    */
    PageInfo<ContractRespDto> queryByPage(String filter,
                                         Integer pageNum,
                                         Integer pageSize);

}
