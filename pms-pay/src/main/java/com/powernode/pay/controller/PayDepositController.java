package com.powernode.pay.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.powernode.pay.domain.PayDeposit;
import com.powernode.pay.service.IPayDepositService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 押金Controller
 * 
 * @author powernode
 * @date 2023-03-06
 */
@RestController
@RequestMapping("/pay/deposit")
public class PayDepositController extends BaseController
{
    @Autowired
    private IPayDepositService payDepositService;

    /**
     * 查询押金列表
     */
    @PreAuthorize("@ss.hasPermi('pay:deposit:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayDeposit payDeposit)
    {
        startPage();
        List<PayDeposit> list = payDepositService.selectPayDepositList(payDeposit);
        return getDataTable(list);
    }

    /**
     * 导出押金列表
     */
    @PreAuthorize("@ss.hasPermi('pay:deposit:export')")
    @Log(title = "押金", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayDeposit payDeposit)
    {
        List<PayDeposit> list = payDepositService.selectPayDepositList(payDeposit);
        ExcelUtil<PayDeposit> util = new ExcelUtil<PayDeposit>(PayDeposit.class);
        util.exportExcel(response, list, "押金数据");
    }

    /**
     * 获取押金详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:deposit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(payDepositService.selectPayDepositById(id));
    }

    /**
     * 新增押金
     */
    @PreAuthorize("@ss.hasPermi('pay:deposit:add')")
    @Log(title = "押金", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayDeposit payDeposit)
    {
        payDeposit.setOperateUser(getUsername());
        payDeposit.setOperateTime(new Date());
        return toAjax(payDepositService.insertPayDeposit(payDeposit));
    }


    /**
     * 退款押金
     */
    @PreAuthorize("@ss.hasPermi('pay:deposit:edit')")
    @Log(title = "押金", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayDeposit payDeposit)
    {
        payDeposit.setRefundUser(getUsername());
        payDeposit.setRefundTime(new Date());
        payDeposit.setState("refunded");
        return toAjax(payDepositService.updatePayDeposit(payDeposit));
    }

    /**
     * 删除押金
     */
    @PreAuthorize("@ss.hasPermi('pay:deposit:remove')")
    @Log(title = "押金", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(payDepositService.deletePayDepositByIds(ids));
    }
}
