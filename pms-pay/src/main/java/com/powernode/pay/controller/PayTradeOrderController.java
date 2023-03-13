package com.powernode.pay.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.powernode.pay.entity.dto.TradeOrderDto;
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
import com.powernode.pay.domain.PayTradeOrder;
import com.powernode.pay.service.IPayTradeOrderService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 支付订单Controller
 * 
 * @author mingyun
 * @date 2023-03-13
 */
@RestController
@RequestMapping("/pay/order")
public class PayTradeOrderController extends BaseController
{
    @Autowired
    private IPayTradeOrderService payTradeOrderService;


    /**
     * 创建支付的订单包含订单详情
     *
     */
    @PostMapping("addPayTradeOrder")
    public AjaxResult addPayTradOrder(@RequestBody TradeOrderDto tradeOrderDto){
        payTradeOrderService.addPayTradeOrder(tradeOrderDto);
        return AjaxResult.success();
    }


    /**
     * 查询支付订单列表
     */
    @PreAuthorize("@ss.hasPermi('pay:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayTradeOrder payTradeOrder)
    {
        startPage();
        List<PayTradeOrder> list = payTradeOrderService.selectPayTradeOrderList(payTradeOrder);
        return getDataTable(list);
    }
}
