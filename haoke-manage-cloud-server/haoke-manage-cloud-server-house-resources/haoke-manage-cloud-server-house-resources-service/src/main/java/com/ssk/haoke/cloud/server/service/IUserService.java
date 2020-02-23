package com.ssk.haoke.cloud.server.service;

import com.ssk.haoke.cloud.server.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.vo.PageInfo;

/**
* 服务接口
*
* @author 代码生成器
*/
public interface IUserService {

    /**
    * 新增
    *
    * @param addReqDto 请求对象
    * @return 新增ID
    */
    Long addUser(UserReqDto addReqDto);

    /**
    * 修改
    *
    * @param modifyReqDto 请求对象
    */
    void modifyUser(UserReqDto modifyReqDto);

    /**
    * 删除
    *
    * @param ids        删除数据ID
    */
    void removeUser(String ids);

    /**
    * 根据id查询
    *
    * @param id id
    * @return   数据
    */
    UserReqDto queryById(Long id);

    /**
    * 分页数据
    *
    * @param filter   查询条件
    * @param pageNum  当前页
    * @param pageSize 页大小
    * @return 分页数据
    */
    PageInfo<UserReqDto> queryByPage(String filter,
                                     Integer pageNum,
                                     Integer pageSize);

}
