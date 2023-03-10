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
        <el-form-item label="是否指定收费项" prop="useFeeItem">
          <el-select v-model="form.useFeeItem" style="width: 100%" placeholder="请选择是否指定收费项">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收费项目" prop="feeItemId" v-if="form.useFeeItem=='Y'">
          <el-select v-model="form.feeItemId" style="width: 100%" placeholder="请选择收费项目" clearable>
            <el-option
              v-for="item in feeItemList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="发生金额" prop="amt">
          <el-input v-model="form.amt" placeholder="请输入发生金额"/>
        </el-form-item>
        <el-form-item label="付款方式" prop="payType">
          <el-select v-model="form.payType" style="width: 100%" placeholder="请选择付款方式">
            <el-option
              v-for="dict in dict.type.fee_method_options"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
        </el-form-item>
      </el-form>
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
import {addPrestore, updatePrestore} from "@/api/pay/prestore";
import {getPayFeeItemByType} from "@/api/pay/payItemConfig";
import SelectBox from "@/views/owner/user/selectBox";

export default {
  name: "addPrestore",
  components: {SelectBox},
  dicts: ['fee_prestore_type_options', 'sys_yes_no', 'fee_method_options'],
  data() {
    return {
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {
        useFeeItem: "N",
        feeUserId: undefined,
        feeUserName: undefined,
        feeItemId: undefined,
        amt: undefined,
        payType: undefined,
        remark: undefined
      },
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
  created() {
    this.loadPayFeeItemByType();
  },
  methods: {
    init() {
      this.open = true
      this.title = "业主费用预存"
      this.form={
        useFeeItem: "N",
        feeUserId: undefined,
        feeUserName: undefined,
        feeItemId: undefined,
        amt: undefined,
        payType: undefined,
        remark: undefined
      }
    },
    //根据类型查询收费项目
    loadPayFeeItemByType() {
      getPayFeeItemByType('house').then(res => {
        this.feeItemList = res.data;
      })
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
      console.log(data)
      this.form.feeUserId = data.id;
      this.form.feeUserName = data.username;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        console.log(this.form)
        if (valid) {
          if(this.form.useFeeItem=="N"){
            this.form.feeItemName="不指定收费项目";
          }else{
            let name="";
            this.feeItemList.filter(item=>{
              if(item.id==this.form.feeItemId){
                name=item.name
              }
            })
            this.form.feeItemName=name;
          }
          this.form.type="pay"
          addPrestore(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open = false;
            this.$emit("after");
          });
        }
      });
    },
  }

}
</script>

<style scoped>

</style>
