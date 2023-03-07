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
import com.powernode.pay.domain.PayPrestore;
import com.powernode.pay.service.IPayPrestoreService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 预存Controller
 * 
 * @author mingyun
 * @date 2023-03-07
 */
@RestController
@RequestMapping("/pay/prestore")
public class PayPrestoreController extends BaseController
{
    @Autowired
    private IPayPrestoreService payPrestoreService;

    /**
     * 查询预存列表
     */
    @PreAuthorize("@ss.hasPermi('pay:prestore:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayPrestore payPrestore)
    {
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
    public void export(HttpServletResponse response, PayPrestore payPrestore)
    {
        List<PayPrestore> list = payPrestoreService.selectPayPrestoreList(payPrestore);
        ExcelUtil<PayPrestore> util = new ExcelUtil<PayPrestore>(PayPrestore.class);
        util.exportExcel(response, list, "预存数据");
    }

    /**
     * 获取预存详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:prestore:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(payPrestoreService.selectPayPrestoreById(id));
    }

    /**
     * 新增预存
     */
    @PreAuthorize("@ss.hasPermi('pay:prestore:add')")
    @Log(title = "预存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayPrestore payPrestore)
    {
        return toAjax(payPrestoreService.insertPayPrestore(payPrestore));
    }

    /**
     * 修改预存
     */
    @PreAuthorize("@ss.hasPermi('pay:prestore:edit')")
    @Log(title = "预存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayPrestore payPrestore)
    {
        return toAjax(payPrestoreService.updatePayPrestore(payPrestore));
    }

    /**
     * 删除预存
     */
    @PreAuthorize("@ss.hasPermi('pay:prestore:remove')")
    @Log(title = "预存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(payPrestoreService.deletePayPrestoreByIds(ids));
    }
}
