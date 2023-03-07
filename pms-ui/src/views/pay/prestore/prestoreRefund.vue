<template>
  <div>
    <el-dialog center :title="title" v-if="open" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="业主ID" prop="feeUserId">
          <el-input v-model="form.feeUserId" style="width: 80%" disabled placeholder="请输入客户ID"/>
          <el-button type="primary" style="margin-left: 10px" @click="selectOwner">选 择</el-button>
        </el-form-item>
        <el-form-item label="业主姓名" prop="feeUserName">
          <el-input v-model="form.feeUserName" disabled placeholder="请输入客户姓名"/>
        </el-form-item>
        <el-form-item label="退款方式" prop="payType">
          <el-select v-model="form.payType" style="width: 100%" placeholder="请选择付款方式">
            <el-option
              v-for="dict in dict.type.fee_method_options"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <!--数据表格-->
      <div class="box">
        <el-tabs v-model="activeName">
          <el-tab-pane label="剩余预存款" name="account">
            <el-table border :data="accountList" size="mini">
              <el-table-column type="id" width="50" label="ID" align="center" />
              <el-table-column prop="feeItemName" label="收费项" />
              <el-table-column prop="amt" label="账户金额" />
              <el-table-column prop="changeMoney" label="退还金额">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.changeMoney" placeholder="请输入" :maxlength="50" clearable :style="{ width: '100%' }"></el-input>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="open=false">取 消</el-button>
      </div>
    </el-dialog>

    <!--选择业主组件-->
    <select-box ref="selectOwner" v-if="showSelectOwner" @select="select"></select-box>

  </div>
</template>

<script>
import {getPayPrestoreAccountByUserOwnerId,refundPrestoreAccount} from "@/api/pay/prestore";
import SelectBox from "@/views/owner/user/selectBox";

export default {
  name: "PrestoreRefund",
  components: {SelectBox},
  dicts: ['fee_method_options'],
  data() {
    return {
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {
        feeUserId: undefined,
        feeUserName: undefined,
        payType: undefined
      },
      activeName: 'account',
      accountList:[],
      // 表单校验
      rules: {
        feeUserName: [
          { required: true, message: "业主姓名不能为空", trigger: "blur" }
        ],
        payType: [
          { required: true, message: "退费方式不能为空", trigger: "blur" }
        ]
      },
      title: "",
      //
      feeItemList: [],
      //是否显示么选择业主
      showSelectOwner: false
    }
  },

  methods: {
    init() {
      this.open = true
      this.title = "业主费用退还"
      this.form={
        feeUserId: undefined,
        feeUserName: undefined,
        payType: undefined
      }
      this.accountList=[];
    },
    //选择业主
    selectOwner() {
      this.showSelectOwner = true;
      this.$nextTick(() => {
        this.$refs.selectOwner.init();
      });
    },
    //选择之后的回调
    select(data) {
      this.form.feeUserId = data.id;
      this.form.feeUserName = data.username;
      getPayPrestoreAccountByUserOwnerId(data.id).then(res=>{
        for (var i = 0; i < res.data.length; i++) {
          res.data[i].changeMoney = '0';
        }
        this.accountList=res.data
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        console.log(valid)
        if (valid) {
          this.form.type = "refund"
          let arr=JSON.parse(JSON.stringify(this.accountList))
          let b=false;
          let c=false;
          arr.filter(item=>{
            if(isNaN(item.changeMoney)){
              c=true;
            }
            if(item.amt<item.changeMoney){
              b=true;
            }
            item.amt=item.changeMoney
          })
          if(c){
            this.$modal.msgError("退费金额只能是数字");
            return;
          }
          if(b){
            this.$modal.msgError("退费金额不能大于预存金额");
            return;
          }
          // refundPrestoreAccount(arr,this.form.payType).then(response => {
          //   this.$modal.msgSuccess("退费成功");
          //   this.open = false;
          //   this.$emit("after");
          // });
        }
      });
    },
  }

}
</script>

<style scoped>

</style>
