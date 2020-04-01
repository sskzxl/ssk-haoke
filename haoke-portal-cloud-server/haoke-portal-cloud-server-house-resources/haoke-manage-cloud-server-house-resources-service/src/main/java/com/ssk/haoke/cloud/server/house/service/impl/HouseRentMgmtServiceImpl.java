package com.ssk.haoke.cloud.server.house.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseRentMgmtReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtDetailRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.eo.HouseRentMgmtEo;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.house.service.HouseResourcesService;
import com.ssk.haoke.cloud.server.house.service.IHouseRentMgmtService;
import com.ssk.haoke.cloud.server.user.api.dto.response.ContractRespDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import com.ssk.haoke.cloud.server.user.api.query.IContractQueryApi;
import com.ssk.haoke.cloud.server.user.api.query.IUserQueryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
* 租房管理服务实现类
*
* @author 代码生成器
*/
@Service
public class HouseRentMgmtServiceImpl extends BaseServiceImpl<HouseRentMgmtEo> implements IHouseRentMgmtService {
    public static final Logger logger = LoggerFactory.getLogger(HouseInspectionReqServiceImpl.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Resource
    private IUserQueryApi userQueryApi;
    @Resource
    private IContractQueryApi contractQueryApi;
    @Resource
    private HouseResourcesService houseResourcesService;
    @Override
    public Long addRentManagement(HouseRentMgmtReqDto addReqDto) {
        HouseRentMgmtEo houseRentMgmtEo = new HouseRentMgmtEo();
        if (null == addReqDto){
            logger.error("新增看房请求参数不得为空");
            throw new RuntimeException("新增看房请求参数不得为空");
        }
        BeanUtils.copyProperties(addReqDto,houseRentMgmtEo,HouseRentMgmtReqDto.class);
        Integer save = super.save(houseRentMgmtEo);
        return save.longValue();
    }

    @Override
    public void modifyRentManagement(HouseRentMgmtReqDto modifyReqDto) {
        if (null == modifyReqDto){
            logger.error("新增看房请求参数不得为空");
            throw new RuntimeException("新增看房请求参数不得为空");
        }
        HouseRentMgmtEo houseRentMgmtEo = new HouseRentMgmtEo();
        BeanUtils.copyProperties(modifyReqDto,houseRentMgmtEo,HouseRentMgmtEo.class);
        super.update(houseRentMgmtEo);
    }

    @Override
    public void removeRentManagement(String ids) {
        String[] split = ids.split(",");
        List idList = CollectionUtils.arrayToList(split);
        super.deleteByIds(idList);
    }

    @Override
    public HouseRentMgmtDetailRespDto getById(Long id) {
        HouseRentMgmtEo rentMgmtEo = super.queryById(id);
        if (null == rentMgmtEo){
            return new HouseRentMgmtDetailRespDto();
        }
        HouseRentMgmtDetailRespDto detailRespDto = new HouseRentMgmtDetailRespDto();
        BeanUtils.copyProperties(rentMgmtEo,detailRespDto);
        //查询房东信息
        Long landlordId = rentMgmtEo.getLandlordId();
        Long tenantId = rentMgmtEo.getTenantId();
        Long houseResourcesId = rentMgmtEo.getHouseResourcesId();
        Long contractId = rentMgmtEo.getContractId();
        if (null != landlordId){
            RestResponse<UserRespDto> landlordRespDto = userQueryApi.queryById(landlordId);
            detailRespDto.setLandLord(landlordRespDto.getData());
        }
        if (null != tenantId){
            RestResponse<UserRespDto> tenantRespDto = userQueryApi.queryById(tenantId);
            detailRespDto.setTenant(tenantRespDto.getData());
        }
        if (null != houseResourcesId){
            HouseResourcesRespDto houseResourcesRespDto = houseResourcesService.queryHouseResourcesById(houseResourcesId);
            detailRespDto.setHouseResources(houseResourcesRespDto);
        }
        if (null != contractId){
            RestResponse<ContractRespDto> contractRespDtoRestResponse = contractQueryApi.queryById(contractId);
            detailRespDto.setContract(contractRespDtoRestResponse.getData());
        }
        return detailRespDto;
    }

    @Override
    public PageInfo<HouseRentMgmtRespDto> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        HouseRentMgmtEo houseRentMgmtEo = new HouseRentMgmtEo();
        try {
            houseRentMgmtEo = OBJECT_MAPPER.readValue(filter, HouseRentMgmtEo.class);
        } catch (IOException e) {
            logger.error("string转对象异常：{}",e);
        }
        IPage<HouseRentMgmtEo> eoIPage = queryPageListByWhere(houseRentMgmtEo, pageNum, pageSize);
        PageInfo respDtoPage = IPage2PageInfo(eoIPage);
        return respDtoPage;
    }
    private PageInfo IPage2PageInfo(IPage eoIPage){
        List<HouseRentMgmtEo> eos = eoIPage.getRecords();
        List<HouseRentMgmtRespDto>  respDtos = Lists.newArrayList();
        for (HouseRentMgmtEo eo : eos) {
            HouseRentMgmtRespDto respDto = new HouseRentMgmtRespDto();
            BeanUtils.copyProperties(eo,respDto);
            respDtos.add(respDto);
        }
        PageInfo<HouseRentMgmtRespDto> respDtoPage = new PageInfo<>();
        respDtoPage.setRecords(respDtos);
        respDtoPage.setPageNum(Integer.parseInt(String.valueOf(eoIPage.getCurrent())));
        respDtoPage.setPageSize(Integer.parseInt(String.valueOf(eoIPage.getSize())));
        Long total = eoIPage.getTotal();
        respDtoPage.setTotal(total.intValue());
        return respDtoPage;
    }
}
