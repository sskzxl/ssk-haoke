package com.ssk.haoke.cloud.server.house.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ssk.haoke.cloud.server.common.BookingStatus;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseBookingReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseBookingRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.eo.EstateEo;
import com.ssk.haoke.cloud.server.house.eo.HouseInspectionReqEo;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.mapper.HouseEstateMapper;
import com.ssk.haoke.cloud.server.house.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.house.service.HouseResourcesService;
import com.ssk.haoke.cloud.server.house.service.IHouseInspectionReqService;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import com.ssk.haoke.cloud.server.user.api.query.IUserQueryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
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
    @Resource
    private HouseResourcesService houseResourcesService;
    @Resource
    private HouseEstateMapper houseEstateMapper;
    @Resource
    private IUserQueryApi userQueryApi;

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
            logger.error("string转对象异常：{}",e);
        }
        IPage<HouseInspectionReqEo> eoIPage = queryPageListByWhere(houseInspectionReqEo, pageNum, pageSize);
        PageInfo respDtoPage = IPage2PageInfo(eoIPage);
        return respDtoPage;
    }

    @Override
    public HouseBookingRespDto getHouseBooking(Long id) {
        if (null == id)return null;
        HouseResourcesRespDto houseResourcesRespDto = houseResourcesService.queryHouseResourcesById(id);
        Long estateId = houseResourcesRespDto.getEstateId();
        //根据楼盘获取地址信息
        EstateEo estateEo = houseEstateMapper.selectById(estateId);
        String address = estateEo.getProvince() + estateEo.getCity() +estateEo.getAddress();
        HouseBookingRespDto houseBookingRespDto = new HouseBookingRespDto();
        houseBookingRespDto.setHouseResourcesRespDto(houseResourcesRespDto);
        houseBookingRespDto.setAddress(address);
        return houseBookingRespDto;
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

    @Override
    public Boolean commitBooking(HouseBookingReqDto houseBookingReqDto) {
        HouseInspectionReqEo houseInspectionReqEo = new HouseInspectionReqEo();
        BeanUtils.copyProperties(houseBookingReqDto,houseInspectionReqEo);
        houseInspectionReqEo.setReqStatus(BookingStatus.UNCONFIRMED.getStatus());//刚提交预约看房，状态为待确认
        houseInspectionReqEo.setReqTime(new Date());
        Long houseResourcesId = houseBookingReqDto.getHouseResourcesId();
        HouseResourcesRespDto houseResourcesRespDto = houseResourcesService.queryHouseResourcesById(houseResourcesId);
        houseInspectionReqEo.setHostId(houseResourcesRespDto.getContactId());
        houseInspectionReqEo.setAddress(houseBookingReqDto.getAddress());
        Integer save = super.save(houseInspectionReqEo);
        return save == 1;
    }

    @Override
    public List<String> getHostNote(Long id) {
        List<String> respStrs = Lists.newArrayList();
        HouseInspectionReqEo houseInspectionReqEo = new HouseInspectionReqEo();
        houseInspectionReqEo.setHostId(id);
        //获取该房东所有看房通知
        List<HouseInspectionReqEo> houseInspectionReqEos = super.queryListByWhere(houseInspectionReqEo);
        for (HouseInspectionReqEo eo : houseInspectionReqEos) {
            StringBuilder respStr = new StringBuilder();
            //王XX 预约 2018-5-14  10:00-12：00 看 回龙观东大街龙腾四区3号楼1单元202室
            Long tenantId = eo.getTenantId();
            String name = getNameById(tenantId);
            respStr.append(name);//姓名
            respStr.append("预约");
            respStr.append(eo.getStartTime());
            respStr.append(eo.getEndTime());
            respStr.append("看");
            respStr.append(eo.getAddress());
//            respStr.append(getMsgByStatus(eo.getReqStatus()));
        }
        return respStrs;
    }



    public List<String> getTenantNote(Long id) {
        List<String> respStrs = Lists.newArrayList();
        HouseInspectionReqEo houseInspectionReqEo = new HouseInspectionReqEo();
        houseInspectionReqEo.setTenantId(id);
        //获取该房东所有看房通知
        List<HouseInspectionReqEo> houseInspectionReqEos = super.queryListByWhere(houseInspectionReqEo);
        for (HouseInspectionReqEo eo : houseInspectionReqEos) {
            StringBuilder respStr = new StringBuilder();
            //您 预约 2018-5-14  10:00-12：00 看 回龙观东大街龙腾四区3号楼1单元202室
            Long tenantId = eo.getTenantId();
            String name = getNameById(tenantId);
            respStr.append("您");
            respStr.append("预约");
            respStr.append(eo.getStartTime());
            respStr.append(eo.getEndTime());
            respStr.append("看");
            respStr.append(eo.getAddress());

        }
        return respStrs;
    }
    private String getNameById(Long tenantId) {
        UserRespDto data = userQueryApi.queryById(tenantId).getData();
        if (null  != data){
            return data.getUsername();
        }else {
            return null;
        }
    }
}
