package com.powernode.pay.mapper;

import java.util.List;
import com.powernode.pay.domain.PayContract;
import com.powernode.pay.entity.vo.ContractVo;
import org.apache.ibatis.annotations.Param;

/**
 * 出租出售合同Mapper接口
 * 
 * @author mingyun
 * @date 2023-03-09
 */
public interface PayContractMapper 
{


    /**
     * 查询出租出售合同列表
     * 
     *
     *
     */
    public List<ContractVo> queryPayContractList(ContractVo contractVo);

    /**
     * 添加合同信息
     * @param payContract
     */
    void insertPayContract(PayContract payContract);

    /**
     * 更新合同
     * @param payContract
     * @return
     */
    int updatePayContract(PayContract payContract);

    /**
     * 通过code查询合同
     *
     * @param code
     * @return
     */
    PayContract selectPayContractByCode(@Param("code") String code);
}
