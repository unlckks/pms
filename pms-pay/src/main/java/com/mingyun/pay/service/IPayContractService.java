package com.mingyun.pay.service;

import java.util.List;

import com.mingyun.pay.domain.PayContract;
import com.mingyun.pay.entity.dto.ContractDto;
import com.mingyun.pay.entity.query.ContractQuery;
import com.mingyun.pay.entity.vo.ContractVo;

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

    /**
     * 查询可生成的编号
     * @param contractQuery
     * @return
     */
    List<ContractVo> selectBillPayContractList(ContractQuery contractQuery);

    /**
     * 根据合同编号查询当前合同所有收费项目
     * @param code
     * @return
     */
    PayContract queryPayContractByCode(String code);

    /**
     * 根据商铺ID有关当前商铺的所有信息
     * @param houseId
     * @param payContractState2
     * @return
     */
    PayContract selectPayContractByHouseId(Long houseId, String payContractState2);


}
