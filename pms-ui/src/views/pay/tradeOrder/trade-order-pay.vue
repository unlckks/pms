<template>
  <el-dialog
    :title="title"
    center
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="500px"
    lock-scroll
  >
    <div style="text-align: center">
      <vue-qr :logoSrc="imageUrl" :text="qrCode" :size="200"></vue-qr>
    </div>
  </el-dialog>
</template>
<script>
import {createPayOrder,checkOrderStateTradeId} from "@/api/pay/order";
import vueQr from 'vue-qr'
export default {
  components:{
    vueQr
  },
  dicts: ['fee_method_options'],
  props: [],
  data() {
    return {
      visible: false,
      tradeId: "",//主订单ID
      qrCode:undefined,
      title:undefined,
      imageUrl: require("@/assets/logo/logo.png"),//icon路径,
      task:undefined
    };
  },
  methods: {
    init(tradeId) {
      this.title=`【${tradeId}】的支付页面`
      this.tradeId = tradeId;
      this.visible = true;
      this.loading=true
      let tx=this;
      createPayOrder(tradeId).then(res=>{
        this.qrCode=res.data.qrCode;
        //创建一个订时任务云查询订单状态 如果状态为2 说明支付成功，否则这个弹出层不关闭
        tx.task=setInterval(function () {
          tx.checkOrderStateTradeId(tx);
        },5000)
      })
    },
    checkOrderStateTradeId(tx){
      checkOrderStateTradeId(tx.tradeId).then(res=>{
        if(res.data==true){
          tx.$message.success("支付成功")
          tx.visible=false
          tx.$emit("select")
          clearInterval(tx.task)//关闭订时任务
        }
      }).catch(()=>{
        clearInterval(tx.task)//关闭订时任务
      })
    }
  }
};
</script>
