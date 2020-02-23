package com.ssk.haoke.cloud.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ssk.haoke.cloud.server.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.service.IHouseInspectionReqService;
import com.ssk.haoke.cloud.server.vo.HouseInspectionReqEo;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
* 看房请求表服务实现类
*
* @author 代码生成器
*/
@Service
public class HouseInspectionReqServiceImpl extends BaseServiceImpl<HouseInspectionReqEo>
        implements IHouseInspectionReqService {

    public static final Logger logger = LoggerFactory.getLogger(HouseInspectionReqServiceImpl.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Override
    public Long addHouseInspectionReq(HouseInspectionReqReqDto addReqDto) {
        HouseInspectionReqEo houseInspectionReqEo = new HouseInspectionReqEo();
        if (null == addReqDto){
            logger.error("新增看房请求参数不得为空");
            throw new RuntimeException("新增看房请求参数不得为空");
        }
        BeanUtils.copyProperties(addReqDto,houseInspectionReqEo,HouseInspectionReqEo.class);
        Integer save = super.save(houseInspectionReqEo);
        return save.longValue();
    }

    @Override
    public void modifyHouseInspectionReq(HouseInspectionReqReqDto modifyReqDto) {
        if (null == modifyReqDto){
            logger.error("新增看房请求参数不得为空");
            throw new RuntimeException("新增看房请求参数不得为空");
        }
        HouseInspectionReqEo houseInspectionReqEo = new HouseInspectionReqEo();
        BeanUtils.copyProperties(modifyReqDto,houseInspectionReqEo,HouseInspectionReqEo.class);
        super.update(houseInspectionReqEo);
    }

    @Override
    public void removeHouseInspectionReq(String ids) {
        String[] split = ids.split(",");
        List idList = CollectionUtils.arrayToList(split);
        super.deleteByIds(idList);
    }

    @Override
    public HouseInspectionRespDto getById(Long id) {
        HouseInspectionReqEo houseInspectionReqEo = super.queryById(id);
        if (null == houseInspectionReqEo){
            return new HouseInspectionRespDto();
        }
        HouseInspectionRespDto houseInspectionRespDto = new HouseInspectionRespDto();
        BeanUtils.copyProperties(houseInspectionReqEo,houseInspectionRespDto);
        return houseInspectionRespDto;
    }

    @Override
    public PageInfo<HouseInspectionRespDto> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        HouseInspectionReqEo houseInspectionReqEo = new HouseInspectionReqEo();
        try {
            houseInspectionReqEo = OBJECT_MAPPER.readValue(filter, HouseInspectionReqEo.class);
        } catch (IOException e) {
            logger.error("string转对象异常");
        }
        IPage<HouseInspectionReqEo> eoIPage = queryPageListByWhere(houseInspectionReqEo, pageNum, pageSize);
        PageInfo respDtoPage = IPage2PageInfo(eoIPage);
        return respDtoPage;
    }

    private PageInfo IPage2PageInfo(IPage eoIPage){
        List<HouseInspectionReqEo> eos = eoIPage.getRecords();
        List<HouseInspectionRespDto>  respDtos = Lists.newArrayList();
        for (HouseInspectionReqEo eo : eos) {
            HouseInspectionRespDto respDto = new HouseInspectionRespDto();
            BeanUtils.copyProperties(eo,respDto);
            respDtos.add(respDto);
        }
        PageInfo<HouseInspectionRespDto> respDtoPage = new PageInfo<>();
        respDtoPage.setRecords(respDtos);
        respDtoPage.setPageNum(Integer.parseInt(String.valueOf(eoIPage.getCurrent())));
        respDtoPage.setPageSize(Integer.parseInt(String.valueOf(eoIPage.getSize())));
        Long total = eoIPage.getTotal();
        respDtoPage.setTotal(total.intValue());
        return respDtoPage;
    }
}
