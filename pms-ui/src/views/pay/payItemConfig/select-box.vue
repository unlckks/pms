<template>
  <div>
    <el-dialog
      title="选择要添加的收费项目"
      :close-on-click-modal="false"
      :visible.sync="visible"
      center
      width="900px"
      height="40%"
      lock-scroll
    >
      <el-table border v-loading="loading" :data="feeItemList"  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="收费项目名"  width="150" align="center" prop="name" />
        <el-table-column label="单价"  width="150" align="center" prop="price" />
        <el-table-column label="数量类型"  width="150" align="center" prop="numType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.fee_num_type_options" :value="scope.row.numType"/>
          </template>
        </el-table-column>
        <el-table-column label="费用周期"  width="150" align="center" prop="period">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.fee_period_options" :value="scope.row.period"/>
          </template>
        </el-table-column>
        <el-table-column label="费用公式"  width="150" align="center" prop="formula">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.fee_formula_options" :value="scope.row.formula"/>
          </template>
        </el-table-column>
        <el-table-column label="自定义"  width="150" align="center" prop="formulaExpression" />
        <el-table-column label="是否产生滞纳金"  width="150" align="center" prop="lateFeeEnable">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.fee_late_enable_options" :value="scope.row.lateFeeEnable"/>
          </template>
        </el-table-column>
        <el-table-column label="滞纳金延迟多久收"  width="150" align="center" prop="lateFeeDays" />
        <el-table-column label="滞纳金比例(%)"  width="150" align="center" prop="lateFeeRate"/>
      </el-table>
      <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dataFormSubmit()">保存</el-button>
        </span>
    </el-dialog>
  </div>
</template>
<script>
import {getPayFeeItemByType} from "@/api/pay/payItemConfig";
export default {
  dicts: ['fee_type_options','fee_num_type_options', 'fee_formula_options', 'fee_period_options', 'fee_late_enable_options'],
  data() {
    return {
      title:undefined,
      loading: false,
      visible: false,
      feeItemList: [],
      type:undefined,
      //是否显示
      showFeeConfig:false,
      selectionList:[]
    };
  },
  methods: {
    init(ids) {
      this.feeItemList=[]
      this.visible=true;
      this.loadFeeItemByType()
    },
    //根据类型查询对应的收费项
    loadFeeItemByType(){
      this.hasIds=[]
      getPayFeeItemByType('house').then(res=>{
        this.feeItemList=res.data;
      })
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectionList =selection
    },
    dataFormSubmit() {
      this.$emit("submit",this.selectionList)
      this.visible=false;
    }
  },
};
</script>

