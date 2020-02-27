package com.ssk.haoke.cloud.server.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.user.api.dto.request.ContractReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.ContractRespDto;
import com.ssk.haoke.cloud.server.user.eo.ContractEo;
import com.ssk.haoke.cloud.server.user.eo.UserEo;
import com.ssk.haoke.cloud.server.user.service.IContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
* 服务实现类
*
* @author 代码生成器
*/
@Service("contractService")
public class ContractServiceImpl  extends BaseServiceImpl<ContractEo> implements IContractService {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Long addContract(ContractReqDto addReqDto) {
        ContractEo contractEo = new ContractEo();
        if (null == addReqDto){
            logger.error("用户合同参数不得为空");
            throw new RuntimeException("用户合同参数不得为空");
        }
        BeanUtils.copyProperties(addReqDto,contractEo,UserEo.class);
        Integer save = super.save(contractEo);
        return save.longValue();
    }

    @Override
    public void modifyContract(ContractReqDto modifyReqDto) {
        if (null == modifyReqDto){
            logger.error("用户新增参数不得为空");
            throw new RuntimeException("用户新增参数不得为空");
        }
        ContractEo contractEo = new ContractEo();
        BeanUtils.copyProperties(modifyReqDto,contractEo,ContractEo.class);
        super.update(contractEo);
    }

    @Override
    public void removeContract(String ids) {
        String[] split = ids.split(",");
        List idList = CollectionUtils.arrayToList(split);
        super.deleteByIds(idList);
    }

    @Override
    public ContractRespDto getById(Long id) {
        ContractEo contractEo = super.queryById(id);
        if (null == contractEo){
            return new ContractRespDto();
        }
        ContractRespDto contractRespDto = new ContractRespDto();
        BeanUtils.copyProperties(contractEo,contractRespDto);
        return contractRespDto;
    }

    @Override
    public PageInfo<ContractRespDto> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        ContractEo contractEo = new ContractEo();
        try {
            contractEo = OBJECT_MAPPER.readValue(filter, ContractEo.class);
        } catch (IOException e) {
            logger.error("string转对象异常");
        }
        IPage<ContractEo> eoIPage = queryPageListByWhere(contractEo, pageNum, pageSize);
        PageInfo respDtoPage = IPage2PageInfo(eoIPage);
        return respDtoPage;
    }
    private PageInfo IPage2PageInfo(IPage eoIPage){
        List<ContractEo> eos = eoIPage.getRecords();
        List<ContractRespDto>  respDtos = Lists.newArrayList();
        for (ContractEo eo : eos) {
            ContractRespDto respDto = new ContractRespDto();
            BeanUtils.copyProperties(eo,respDto);
            respDtos.add(respDto);
        }
        PageInfo<ContractRespDto> respDtoPage = new PageInfo<>();
        respDtoPage.setRecords(respDtos);
        respDtoPage.setPageNum(Integer.parseInt(String.valueOf(eoIPage.getCurrent())));
        respDtoPage.setPageSize(Integer.parseInt(String.valueOf(eoIPage.getSize())));
        Long total = eoIPage.getTotal();
        respDtoPage.setTotal(total.intValue());
        return respDtoPage;
    }
}
