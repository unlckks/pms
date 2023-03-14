<template>
  <el-dialog
    title="订单详情"
    center
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="900px"
    lock-scroll
  >
    <el-table :data="tradeOrderItemList" border show-summary  v-loading="loading">
      <el-table-column prop="orderId" label="编号" align="center"/>
      <el-table-column prop="itemName" label="项目名称" align="center"/>
      <el-table-column prop="price" label="单价" align="center"/>
      <el-table-column prop="num" label="数量" align="center"/>
      <el-table-column prop="amt" label="金额" align="center"/>
    </el-table>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
    </span>
  </el-dialog>
</template>
<script>
import {queryTradeOrderItemByTradeId} from "@/api/pay/order";

export default {
  dicts: ['fee_method_options'],
  props: [],
  data() {
    return {
      tradeOrderItemList:[],
      loading: false,
      visible: false,
      //主订单ID
      tradeId: "",
    };
  },
  methods: {
    init(tradeId) {
      this.tradeId = tradeId;
      this.visible = true;
      this.loading=true
      queryTradeOrderItemByTradeId(this.tradeId).then(res=>{
        this.tradeOrderItemList=res.data;
        this.loading=false;
      })
    }
  }
};
</script>
