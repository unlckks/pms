package com.mingyun.pay.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.mingyun.common.utils.StringUtils;
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
import com.mingyun.common.annotation.Log;
import com.mingyun.common.core.controller.BaseController;
import com.mingyun.common.core.domain.AjaxResult;
import com.mingyun.common.enums.BusinessType;
import com.mingyun.pay.domain.PayFeeItem;
import com.mingyun.pay.service.IPayFeeItemService;
import com.mingyun.common.utils.poi.ExcelUtil;
import com.mingyun.common.core.page.TableDataInfo;

/**
 * 收费项目管理Controller
 *
 * @author mingyun
 * @date 2023-03-06
 */
@RestController
@RequestMapping("/pay/payItemConfig")
public class PayFeeItemController extends BaseController {
    @Autowired
    private IPayFeeItemService payFeeItemService;

    /**
     * 查询收费项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('pay:payItemConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayFeeItem payFeeItem) {
        startPage();
        List<PayFeeItem> list = payFeeItemService.selectPayFeeItemList(payFeeItem);
        return getDataTable(list);
    }

    /**
     * 导出收费项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('pay:payItemConfig:export')")
    @Log(title = "收费项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayFeeItem payFeeItem) {
        List<PayFeeItem> list = payFeeItemService.selectPayFeeItemList(payFeeItem);
        ExcelUtil<PayFeeItem> util = new ExcelUtil<PayFeeItem>(PayFeeItem.class);
        util.exportExcel(response, list, "收费项目管理数据");
    }

    /**
     * 获取收费项目管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:payItemConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(payFeeItemService.selectPayFeeItemById(id));
    }

    /**
     * 新增收费项目管理
     */
    @PreAuthorize("@ss.hasPermi('pay:payItemConfig:add')")
    @Log(title = "收费项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayFeeItem payFeeItem) {
        return toAjax(payFeeItemService.insertPayFeeItem(payFeeItem));
    }

    /**
     * 修改收费项目管理
     */
    @PreAuthorize("@ss.hasPermi('pay:payItemConfig:edit')")
    @Log(title = "收费项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayFeeItem payFeeItem) {
        return toAjax(payFeeItemService.updatePayFeeItem(payFeeItem));
    }

    /**
     * 删除收费项目管理
     */
    @PreAuthorize("@ss.hasPermi('pay:payItemConfig:remove')")
    @Log(title = "收费项目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(payFeeItemService.deletePayFeeItemByIds(ids));
    }


    /**
     * 根据项目类型查询收费项目
     */
    @GetMapping("getPayFeeItemByType/{type}")
    public AjaxResult getPayFeeItemByType(@PathVariable String type) {
        List<PayFeeItem> payFeeItems = this.payFeeItemService.queryPayFeeItemByType(type);
        return AjaxResult.success(payFeeItems);
    }

    /**
     * 根据类型查询已配置默认收费项目
     */
    @GetMapping("getDefaultPayFeeItemByType/{type}")
    public AjaxResult getDefaultPayFeeItemByType(@PathVariable String type) {
        if (StringUtils.isBlank(type)) {
            return AjaxResult.error("类型不能为空");
        }
        List<PayFeeItem> payFeeItems = this.payFeeItemService.queryDefaultPayFeeItemByType(type);
        return AjaxResult.success(payFeeItems);
    }

    /**
     * 删除默认收费项目（根据收费项目ID和类型）
     */
    @DeleteMapping("/delDefaultPayFeeItem/{id}/{type}")
    public AjaxResult delDefaultPayFeeItem(@PathVariable Long id, @PathVariable String type) {
        if (id == null || StringUtils.isBlank(type)) {
            return AjaxResult.error("ID或类型不能为空的！");
        }
        return toAjax(payFeeItemService.deleteDefaultPayFeeItemByIdAndType(id, type));
    }

    /**
     * 新增默认收费项目
     */
    @PostMapping("addDefaultPayItemConfig")
    public AjaxResult addDefaultPayItemConfig(String type, Long[] ids) {
        if (StringUtils.isBlank(type)) {
            AjaxResult.error("类型不能为空");
        }
        if (ids == null || ids.length == 0) {
            AjaxResult.error("选中项目不能为空");
        }
        //进行保存
        return  toAjax(this.payFeeItemService.addDefaultPayItemConfig(type ,ids));
    }

}
