package com.mingyun.pay.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.mingyun.common.utils.uuid.IdUtils;
import com.mingyun.pay.constants.PayConstants;
import com.mingyun.pay.domain.PayPrestoreAccount;
import com.mingyun.system.service.ISysConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mingyun.common.annotation.Log;
import com.mingyun.common.core.controller.BaseController;
import com.mingyun.common.core.domain.AjaxResult;
import com.mingyun.common.enums.BusinessType;
import com.mingyun.pay.domain.PayPrestore;
import com.mingyun.pay.service.IPayPrestoreService;
import com.mingyun.common.utils.poi.ExcelUtil;
import com.mingyun.common.core.page.TableDataInfo;

/**
 * 预存Controller
 *
 * @author mingyun
 * @date 2023-03-07
 */
@RestController
@RequestMapping("/pay/prestore")
public class PayPrestoreController extends BaseController {
    @Autowired
    private IPayPrestoreService payPrestoreService;

    @Resource
    private ISysConfigService configService ;

    /**
     * 查询预存列表
     */
    @PreAuthorize("@ss.hasPermi('pay:prestore:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayPrestore payPrestore) {
        startPage();
        List<PayPrestore> list = payPrestoreService.selectPayPrestoreList(payPrestore);
        return getDataTable(list);
    }

    /**
     * 导出预存列表
     */
    @PreAuthorize("@ss.hasPermi('pay:prestore:export')")
    @Log(title = "预存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayPrestore payPrestore) {
        List<PayPrestore> list = payPrestoreService.selectPayPrestoreList(payPrestore);
        ExcelUtil<PayPrestore> util = new ExcelUtil<PayPrestore>(PayPrestore.class);
        util.exportExcel(response, list, "预存数据");
    }

    /**
     * 获取预存详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:prestore:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(payPrestoreService.selectPayPrestoreById(id));
    }

    /**
     * 新增预存
     */
    @PreAuthorize("@ss.hasPermi('pay:prestore:add')")
    @Log(title = "预存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayPrestore payPrestore) {
        //设置操作人和时间
        payPrestore.setOperateTime(new Date());
        payPrestore.setOperateUser( getUsername());
        //设置创建人
        payPrestore.setCreateBy(getUsername());
        //设置单号
        String config =configService.selectConfigByKey(PayConstants.PAY_PREFIX_PAY);
        payPrestore.setPayNo(IdUtils.createNoWithPrefix(config));
        return toAjax(payPrestoreService.insertPayPrestore(payPrestore));
    }

    /**
     * 根据业主ID查询业主的所有账户的信息
     *
     * @param ownerId
     * @return
     */
    @GetMapping("getPayPrestoreAccountByUserOwnerId/{ownerId}")
    public AjaxResult getPayPrestoreAccountByUserOwnerId(@PathVariable Long ownerId) {
        if (ownerId == null) {
            return AjaxResult.error("主ID不能为空!");
        }
        List<PayPrestoreAccount> accounts = this.payPrestoreService.queryPayPrestoreAccountByUserOwnerId(ownerId);
        if (accounts == null || accounts.size() == 0) {
            return AjaxResult.error("当前业主没有可退的预存款");
        }
        return AjaxResult.success(accounts);
    }

    /**
     * 退款
     * @param type
     * @param accounts
     * @return
     */
    @PostMapping("refundPrestoreAccount/{type}")
    public AjaxResult refundPrestoreAccount(@PathVariable String type, @RequestBody PayPrestoreAccount[] accounts) {
            if (accounts == null || accounts.length == 0 ){
                return AjaxResult.error("退费项目不能为空");
            }
            this.payPrestoreService.refundPrestoreAccount(type , accounts ,getUsername());
            return  AjaxResult.success() ;
    }
}
