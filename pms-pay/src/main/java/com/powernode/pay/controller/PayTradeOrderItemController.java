package com.powernode.pay.controller;

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
import com.powernode.pay.domain.PayTradeOrderItem;
import com.powernode.pay.service.IPayTradeOrderItemService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 支付订单详情Controller
 * 
 * @author mingyun
 * @date 2023-03-13
 */
@RestController
@RequestMapping("/pay/item")
public class PayTradeOrderItemController extends BaseController
{
    @Autowired
    private IPayTradeOrderItemService payTradeOrderItemService;

    /**
     * 查询支付订单详情列表
     */
    @PreAuthorize("@ss.hasPermi('pay:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayTradeOrderItem payTradeOrderItem)
    {
        startPage();
        List<PayTradeOrderItem> list = payTradeOrderItemService.selectPayTradeOrderItemList(payTradeOrderItem);
        return getDataTable(list);
    }

    /**
     * 导出支付订单详情列表
     */
    @PreAuthorize("@ss.hasPermi('pay:item:export')")
    @Log(title = "支付订单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayTradeOrderItem payTradeOrderItem)
    {
        List<PayTradeOrderItem> list = payTradeOrderItemService.selectPayTradeOrderItemList(payTradeOrderItem);
        ExcelUtil<PayTradeOrderItem> util = new ExcelUtil<PayTradeOrderItem>(PayTradeOrderItem.class);
        util.exportExcel(response, list, "支付订单详情数据");
    }

    /**
     * 获取支付订单详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:item:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") String orderId)
    {
        return success(payTradeOrderItemService.selectPayTradeOrderItemByOrderId(orderId));
    }

    /**
     * 新增支付订单详情
     */
    @PreAuthorize("@ss.hasPermi('pay:item:add')")
    @Log(title = "支付订单详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayTradeOrderItem payTradeOrderItem)
    {
        return toAjax(payTradeOrderItemService.insertPayTradeOrderItem(payTradeOrderItem));
    }

    /**
     * 修改支付订单详情
     */
    @PreAuthorize("@ss.hasPermi('pay:item:edit')")
    @Log(title = "支付订单详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayTradeOrderItem payTradeOrderItem)
    {
        return toAjax(payTradeOrderItemService.updatePayTradeOrderItem(payTradeOrderItem));
    }

    /**
     * 删除支付订单详情
     */
    @PreAuthorize("@ss.hasPermi('pay:item:remove')")
    @Log(title = "支付订单详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String[] orderIds)
    {
        return toAjax(payTradeOrderItemService.deletePayTradeOrderItemByOrderIds(orderIds));
    }
}
