<template>
  <el-dialog
    :title="'常规项目收费'"
    center
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="900px"
    lock-scroll
  >
    <div class="Jflex-main" style="overflow: hidden; height: 100%; padding-bottom: 18px">
      <el-table :data="fee.paymentBills">
        <el-table-column prop="itemName" label="收费项名称" align="left" width="150"/>
        <el-table-column prop="beginTime" label="费用开始时间" align="left" width="100"/>
        <el-table-column prop="endTime" label="费用结束时间" align="left" width="100"/>
        <el-table-column prop="price" label="单价" align="center"/>
        <el-table-column prop="num" label="数量" algin="center"/>
        <el-table-column prop="amt" label="金额" algin="center"/>
        <el-table-column prop="amt" label="应收金额" align="left"/>
      </el-table>
      <div style="margin-top: 5px">
        <el-form @submit.native.prevent size="medium" :style="'width:100%'" label-width="70px" label-position="left">
          <el-row :gutter="24" :style="'width:100%;'">
            <el-col :span="8">
              <el-form-item label="实际应收" prop="feeUser">
                <el-input
                  v-model="fee.receivableMoney"
                  placeholder="请输入实际应收"
                  :disabled="true"
                >
                  <template slot="append">
                    元
                  </template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="收款金额" prop="feeUser">
                <el-input
                  v-model="fee.payMoney"
                  placeholder="请输入收款金额"
                >
                  <template slot="append">
                    元
                  </template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
                  <el-form-item label="付款方式" prop="payType">
                    <el-select v-model="fee.payType" placeholder="请选择付款方式" clearable>
                      <el-option
                        v-for="dict in dict.type.fee_method_options"
                        :key="dict.value"
                        :label="dict.label"
                        :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </div>
    <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">取 消</el-button>
            <el-button type="primary" @click="handleSubmit()">确 定</el-button>
        </span>
  </el-dialog>
</template>
<script>
import {addPayTradeOrder} from "@/api/pay/order";

export default {
  components: {},
  dicts: ['fee_method_options'],
  props: [],
  data() {
    return {
      fee: {
        receivableMoney: 0,
        payMoney: 0,
        paymentBills: [],
        payType: undefined,
      },
      loading: false,
      visible: false,
      listLoading: true,
      houseId:"",
    };
  },
  methods: {
    init(payList,houseId) {
      this.houseId=houseId;
      console.log(payList)
      console.log(houseId)
      this.visible = true;
      this.fee.paymentBills = payList;
      this.fee.paymentBills.filter(item=>{
        this.fee.receivableMoney+=item.amt;
      })
      this.fee.payMoney=this.fee.receivableMoney;
    },
    handleSubmit() {
      this.$confirm('确认完成订单提交及付款操作吗?', '提示', {
        type: 'warning',
      }).then(() => {
        let data={payType:this.fee.payType,houseId:this.houseId,payOrders:this.fee.paymentBills}
        console.log(data)
        addPayTradeOrder(data).then(res=>{
          this.$message.success("操作成功")
          this.$emit("select")
          this.visible=false;
        })
      }).catch(() => {

      });
    }
  }
};
</script>
