package com.ssk.haoke.cloud.manage.api.service.impl;

import com.ssk.haoke.cloud.server.house.api.IHouseInspectionReqApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseInspectionReqQueryApi;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("houseInspectionReqService")
public class HouseInspectionReqServiceImpl {
    
    @Resource
    private IHouseInspectionReqQueryApi houseInspectionReqQueryApi;
    @Resource
    private IHouseInspectionReqApi houseInspectionReqApi;
    //删除看房请求
    public RestResponse<Void> delete(String ids){
        return this.houseInspectionReqApi.removeHouseInspectionReq(ids);
    }
    //增加看房请求
    public RestResponse<Long> save(HouseInspectionReqReqDto houseInspectionReqReqDto) {
        RestResponse<Long> result = this.houseInspectionReqApi.addHouseInspectionReq(houseInspectionReqReqDto);
        return result;
    }
    //查询看房请求列表
    public RestResponse<List<Map<String,String>>> queryList(Integer pageNum, Integer pageSize, String filter) {
        RestResponse<List<Map<String,String>>> response = this.houseInspectionReqQueryApi.queryByPage(filter, pageNum, pageSize);

        return response;
    }
    /**
     * @param id
     * @return
     */
    public RestResponse<HouseInspectionRespDto> queryById(Long id){
        return this.houseInspectionReqQueryApi.queryById(id);
    }
    /*
        更新看房请求
     */
    public RestResponse<Void> update(HouseInspectionReqReqDto houseInspectionReqReqDto) {
        return this.houseInspectionReqApi.modifyHouseInspectionReq(houseInspectionReqReqDto);
    }


}