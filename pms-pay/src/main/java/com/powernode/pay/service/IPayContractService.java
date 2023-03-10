package com.powernode.pay.service;

import java.util.List;
import com.powernode.pay.entity.dto.ContractDto;
import com.powernode.pay.entity.vo.ContractVo;

/**
 * 出租出售合同Service接口
 * 
 * @author mingyun
 * @date 2023-03-09
 */
public interface IPayContractService 
{
    /**
     * 查询出租出售合同列表
     *
     * @return 出租出售合同集合
     */
    public List<ContractVo> selectPayContractList(ContractVo contractQuery);

    /**
     * 添加合同
     * @param contractDto
     */
    void addContract(ContractDto contractDto);

    /**
     * 根据合同code删除合同
     * @param code
     * @return
     */
    int cancelContractByCode(String code);

}
