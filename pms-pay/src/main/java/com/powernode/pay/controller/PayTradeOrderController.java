package com.powernode.pay.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.powernode.pay.constants.PayConstants;
import com.powernode.pay.domain.PayTradeOrderItem;
import com.powernode.pay.entity.dto.TradeOrderDto;
import com.powernode.pay.pay.PayService;
import org.springframework.beans.factory.annotation.Value;
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
public class PayTradeOrderController extends BaseController {
    @Autowired
    private IPayTradeOrderService payTradeOrderService;

    @Value("${app.notify-url}")
    private String notifyUrl;

    /**
     * 创建支付的订单包含订单详情
     */
    @PostMapping("addPayTradeOrder")
    public AjaxResult addPayTradOrder(@RequestBody TradeOrderDto tradeOrderDto) {
        payTradeOrderService.addPayTradeOrder(tradeOrderDto);
        return AjaxResult.success();
    }


    /**
     * 查询支付订单列表
     */
    @PreAuthorize("@ss.hasPermi('pay:order:list')")
    @GetMapping("/listOrder")
    public TableDataInfo list(PayTradeOrder payTradeOrder) {
        startPage();
        List<PayTradeOrder> list = payTradeOrderService.selectPayTradeOrderList(payTradeOrder);
        return getDataTable(list);
    }

    /**
     * 根据主订单ID查询订单详情
     */
    @GetMapping("queryTradeOrderItemByTradeId/{tradeId}")
    public AjaxResult queryTradeOrderItemByTradeId(@PathVariable String tradeId) {
        List<PayTradeOrderItem> orderItems = this.payTradeOrderService.selectTradeOrderItemByTradeId(tradeId);
        return AjaxResult.success(orderItems);
    }

    /**
     * 创建支付
     */
    @GetMapping("createPayOrder/{tradeId}")
    public AjaxResult createPayOrder(@PathVariable String tradeId) {
        PayTradeOrder tradeOrder = this.payTradeOrderService.selectPayTradeOrderById(tradeId);
        if (tradeOrder == null) {
            AjaxResult.error("订单不存在");
        }
        if (tradeOrder.getState().equals(PayConstants.PAY_ORDER_STATE_2)) {
            AjaxResult.error("当前订单支付成功");
        }
        //创建支付
        Map<String, Object> map = PayService.pay(tradeOrder.getId(), tradeOrder.getSubject(),
                tradeOrder.getAmount().toString(), "0", tradeOrder.getBody(), notifyUrl);
        if (!map.get("code").equals(200)) {
            AjaxResult.error("创建支付订单失败");
        }
        return AjaxResult.success(map);
    }

    /**
     * 根据支付订单ID检查订单支付状态
     */
    @GetMapping("checkOrderStateTradeId/{tradeId}")
    public AjaxResult checkOrderStateTradeId(@PathVariable String tradeId) {
        //查询订单id
        PayTradeOrder tradeOrder = this.payTradeOrderService.selectPayTradeOrderById(tradeId);
        if (tradeOrder.getState().equals(PayConstants.PAY_ORDER_STATE_2)) {
            return AjaxResult.success("支付成功", true);
        }
        return AjaxResult.success();
    }

}


