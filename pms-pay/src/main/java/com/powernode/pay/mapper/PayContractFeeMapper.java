package com.powernode.pay.mapper;

import java.util.List;
import com.powernode.pay.domain.PayContractFee;

/**
 * 合同收费项目Mapper接口
 * 
 * @author mingyun
 * @date 2023-03-09
 */
public interface PayContractFeeMapper 
{
    /**
     * 查询合同收费项目
     * 
     * @param id 合同收费项目主键
     * @return 合同收费项目
     */
    public PayContractFee selectPayContractFeeById(Long id);

    /**
     * 查询合同收费项目列表
     * 
     * @param payContractFee 合同收费项目
     * @return 合同收费项目集合
     */
    public List<PayContractFee> selectPayContractFeeList(PayContractFee payContractFee);

    /**
     * 新增合同收费项目
     * 
     * @param payContractFee 合同收费项目
     * @return 结果
     */
    public int insertPayContractFee(PayContractFee payContractFee);

    /**
     * 修改合同收费项目
     * 
     * @param payContractFee 合同收费项目
     * @return 结果
     */
    public int updatePayContractFee(PayContractFee payContractFee);

    /**
     * 删除合同收费项目
     * 
     * @param id 合同收费项目主键
     * @return 结果
     */
    public int deletePayContractFeeById(Long id);

    /**
     * 批量删除合同收费项目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayContractFeeByIds(Long[] ids);
}
