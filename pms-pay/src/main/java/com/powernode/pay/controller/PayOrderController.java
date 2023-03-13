package com.powernode.pay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.powernode.asset.domain.AssetBuilding;
import com.powernode.asset.domain.AssetHouse;
import com.powernode.asset.domain.AssetHouseBlock;
import com.powernode.asset.service.IAssetBuildingService;
import com.powernode.asset.service.IAssetHouseBlockService;
import com.powernode.asset.service.IAssetHouseService;
import com.powernode.common.utils.StringUtils;
import com.powernode.owner.domain.OwnerUser;
import com.powernode.owner.service.IOwnerUserService;
import com.powernode.pay.constants.PayConstants;
import com.powernode.pay.domain.PayContract;
import com.powernode.pay.service.IPayContractService;
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
import com.powernode.pay.domain.PayOrder;
import com.powernode.pay.service.IPayOrderService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 订单Controller
 *
 * @author mingyun
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/pay/order")
public class PayOrderController extends BaseController {
    @Autowired
    private IPayOrderService payOrderService;
    @Resource
    private IPayContractService contractService;
    @Resource
    private IAssetHouseBlockService blockService;
    @Resource
    private IAssetBuildingService buildingService;
    @Resource
    private IAssetHouseService houseService;
    @Resource
    private IOwnerUserService ownerUserService;

    /**
     * 添加常规收费项目
     *
     * @param code
     * @param ids
     * @return
     */
    @GetMapping("addOrderNormal")
    public AjaxResult addOrderNormal(String code, long[] ids) {
        if (StringUtils.isBlank(code)) {
            return AjaxResult.error("合同编号不能为空");
        }
        if (ids == null || ids.length == 0) {
            return AjaxResult.error("至少一个收费项目");
        }
        PayContract payContract = contractService.queryPayContractByCode(code);
        if (payContract.getState().equals(PayConstants.PAY_CONTRACT_STATE_2)) {
            return AjaxResult.error("当前合同编号已撤销");
        }
        //进行保存
        this.payOrderService.addOrderNormal(code, ids);
        return AjaxResult.success();
    }

    /**
     * 根据商铺ID有关当前商铺的所有信息
     */
    @GetMapping("getAllContentByHouseId/{houseId}")
    public AjaxResult getAllContentByHouseId(@PathVariable Long houseId) {
        //进行判断
        if (houseId == null) {
            return AjaxResult.error("ID不能为空");
        }
        //根据houseId查询未支付的订单
        PayOrder order = new PayOrder();
        order.setHouseId(houseId);
        order.setPayState(PayConstants.PAY_STATE_NO_PAY);
        List<PayOrder> payOrders = this.payOrderService.selectPayOrderList(order);
        if (payOrders == null || payOrders.size() == 0) {
            return AjaxResult.error("当前菜单没有待支付的订单");
        }
        //进行查询商铺的所有信息
        AssetHouse assetHouse = this.houseService.selectAssetHouseById(houseId);
        //查询楼栋的所有的信息
        AssetBuilding assetBuilding = this.buildingService.selectAssetBuildingById(assetHouse.getBuildingId());
        //查询商圈的所有信息
        AssetHouseBlock assetHouseBlock =this.blockService.selectAssetHouseBlockById(assetHouse.getBlockId());
        //根据商铺id和状态查询合同信息
        PayContract payContract  =this.contractService.selectPayContractByHouseId(houseId,PayConstants.PAY_CONTRACT_STATE_1);
        //查询业主
        OwnerUser  ownerUser  = this.ownerUserService.selectOwnerUserById(payContract.getOwnerId());
        Map<String , Object> data =new HashMap<>();
        //存放商圈的所有信息
        data.put("blockContent",assetHouseBlock);
        //进行存放楼栋的所有的信息
        data.put("buildingContent",assetBuilding);
        //进行存放商铺的所有信息
        data.put("houseContent",assetHouse);
        //业主所有信息
        data.put("ownerUserContent",ownerUser);
        //合同信息
        data.put("contractContent",payContract);
        //收费顶单
        data.put("payOrderList",payOrders);
        return AjaxResult.success(data);
    }

}
