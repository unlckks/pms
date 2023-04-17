package com.mingyun.pay.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.mingyun.common.utils.StringUtils;
import com.mingyun.common.utils.uuid.IdUtils;
import com.mingyun.pay.constants.PayConstants;
import com.mingyun.pay.service.IPayTempService;
import com.mingyun.system.service.ISysConfigService;
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
import com.mingyun.pay.domain.PayTemp;
import com.mingyun.common.utils.poi.ExcelUtil;
import com.mingyun.common.core.page.TableDataInfo;

/**
 * 临时收费Controller
 * 
 * @author mingyun
 * @date 2023-03-07
 */
@RestController
@RequestMapping("/pay/temp")
public class PayTempController extends BaseController
{
    @Resource
    private IPayTempService payTempService;

    @Autowired
    private ISysConfigService configService  ;

    /**
     * 查询临时收费列表
     */
    @PreAuthorize("@ss.hasPermi('pay:temp:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayTemp payTemp)
    {
        startPage();
        List<PayTemp> list = payTempService.selectPayTempList(payTemp);
        return getDataTable(list);
    }

    /**
     * 导出临时收费列表
     */
    @PreAuthorize("@ss.hasPermi('pay:temp:export')")
    @Log(title = "临时收费", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayTemp payTemp)
    {
        List<PayTemp> list = payTempService.selectPayTempList(payTemp);
        ExcelUtil<PayTemp> util = new ExcelUtil<PayTemp>(PayTemp.class);
        util.exportExcel(response, list, "临时收费数据");
    }

    /**
     * 获取临时收费详细信息
     */
    @PreAuthorize("@ss.hasPermi('pay:temp:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(payTempService.selectPayTempById(id));
    }

    /**
     * 新增临时收费
     */
    @PreAuthorize("@ss.hasPermi('pay:temp:add')")
    @Log(title = "临时收费", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayTemp payTemp)
    {
        String prefix = configService.selectConfigByKey(PayConstants.PAY_PREFIX_PAY);
        if (StringUtils.isBlank(prefix)){
            return AjaxResult.error("没设置,找管理员");
        }
        //生成支付单号
        payTemp.setPayNo(IdUtils.createNoWithPrefix(prefix));
        //添加操作和时间
        payTemp.setOperateUser (getUsername());
        payTemp.setOperateTime(new Date());
        //设置状态
        payTemp.setState(PayConstants.PAY_STATE_PAYED);
        //设置创建
        payTemp.setCreateBy(getUsername());
        return toAjax(payTempService.insertPayTemp(payTemp));
    }

    /**
     * 退款临时收费
     */
    @PreAuthorize("@ss.hasPermi('pay:temp:edit')")
    @Log(title = "临时收费", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayTemp payTemp)
    {
        String prefix = configService.selectConfigByKey(PayConstants.PAY_PREFIX_PAY) ;
        if (StringUtils.isBlank(prefix)){
            return AjaxResult.error("没设置,找管理员");
        }
        //生成支付单号
        payTemp.setRefundNo(IdUtils.createNoWithPrefix(prefix));
        //添加操作和时间
        payTemp.setRefundUser (getUsername());
        payTemp.setRefundTime(new Date());
        //设置状态
        payTemp.setState(PayConstants.PAY_STATE_REFUND);
        //设置创建
        payTemp.setUpdateBy(getUsername());
        return toAjax(payTempService.updatePayTemp(payTemp));
    }

    /**
     * 删除临时收费
     */
    @PreAuthorize("@ss.hasPermi('pay:temp:remove')")
    @Log(title = "临时收费", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(payTempService.deletePayTempByIds(ids));
    }
}
