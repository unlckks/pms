package com.powernode.pay.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.powernode.common.utils.StringUtils;
import com.powernode.pay.constants.PayConstants;
import com.powernode.pay.domain.PayFeeItem;
import com.powernode.pay.entity.dto.ContractDto;
import com.powernode.pay.entity.query.ContractQuery;
import com.powernode.pay.entity.vo.ContractVo;
import com.powernode.pay.service.IPayFeeItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.powernode.common.annotation.Log;
import com.powernode.common.core.controller.BaseController;
import com.powernode.common.core.domain.AjaxResult;
import com.powernode.common.enums.BusinessType;
import com.powernode.pay.domain.PayContract;
import com.powernode.pay.service.IPayContractService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 出租出售合同Controller
 *
 * @author mingyun
 * @date 2023-03-09
 */
@RestController
@RequestMapping("/pay/contract")
public class PayContractController extends BaseController {
    @Resource
    private IPayContractService payContractService;

    @Resource
    private IPayFeeItemService payFeeItemService;
    /**
     * 查询出租出售合同列表
     */
    @PreAuthorize("@ss.hasPermi('pay:contract:list')")
    @GetMapping("/listContract")
    public TableDataInfo list(ContractVo contractQuery) {
        startPage();
        List<ContractVo> list = payContractService.selectPayContractList(contractQuery);
        return getDataTable(list);
    }

    /**
     * 进行添加合同
     */
    @PostMapping("addContract")
    public AjaxResult addContract(@RequestBody @Validated ContractDto contractDto) {
        this.payContractService.addContract(contractDto);
        return AjaxResult.success();
    }

    /**
     * 根据合同编号撤销合同
     *
     * @param code
     * @return
     */
    @GetMapping("cancelContract/{code}")
    public AjaxResult cancelContract(@PathVariable String code) {
        if (StringUtils.isBlank(code)) {
            return AjaxResult.error("合同编号不能为空");
        }
        return toAjax(this.payContractService.cancelContractByCode(code));
    }

    /**
     * 查询可生成的编号
     */
    @GetMapping("listBillContract")
    public TableDataInfo listBillContract(ContractQuery contractQuery) {
        startPage();
        List<ContractVo> list = payContractService.selectBillPayContractList(contractQuery);
        return getDataTable(list);
    }

    /**
     * 根据合同编号查询当前合同所有收费项目
     */
    @GetMapping("getContractFeeItemsByCode/{code}")
    public AjaxResult getContractFeeItemsByCode(@PathVariable String code) {
        //根据合同编号查询合同数据
        PayContract payContract = this.payContractService.queryPayContractByCode(code);
        if (payContract == null) {
            return AjaxResult.error("当前【" + code + "】合同编号的合同不存在");
        }
        if (payContract.getState().equals(PayConstants.PAY_CONTRACT_STATE_2)) {
            return AjaxResult.error("当前【" + code + "】合同编号的合同已撤销，不用生成");
        }
        List<PayFeeItem> feeItems = this.payFeeItemService.queryContractFeeItemsByContractId(payContract.getId());
        if (feeItems == null || feeItems.size() == 0) {
            return AjaxResult.error("当前【" + code + "】合同编号不存在收费项目");
        }
        return AjaxResult.success(feeItems);
    }
}
