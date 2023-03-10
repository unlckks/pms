<template>
  <div>
    <el-dialog
      :title="title==undefined?'未传入标题':title"
      :close-on-click-modal="false"
      :visible.sync="visible"
      center
      width="1000px"
      height="50%"
      lock-scroll
    >
      <el-table border v-loading="loading" :data="feeItemList">
        <el-table-column prop="id" label="项目ID" align="center"/>
        <el-table-column prop="name" label="收费项名" align="center"/>
        <el-table-column prop="price" label="单价" align="center"/>
        <el-table-column label="操作" width="80" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="handleDel(scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="table-actions" @click="handleSelect" style="text-align: center">
        <el-button type="text" icon="el-icon-plus">新增收费项</el-button>
      </div>
    </el-dialog>

    <select-box v-if="showFeeConfig" ref="feeConfig" @submit="dataFormSubmit"></select-box>

  </div>
</template>
<script>
import {getDefaultPayFeeItemByType, delDefaultPayFeeItem, addDefaultPayItemConfig} from "@/api/pay/payItemConfig";
import selectBox from "@/views/pay/payItemConfig/select-box";

export default {
  props: [],
  components: {
    selectBox
  },
  data() {
    return {
      title: undefined,
      loading: false,
      visible: false,
      feeItemList: [],
      type: undefined,
      //是否显示
      showFeeConfig: false,
      hasIds: []
    };
  },
  methods: {
    init(type, title) {
      this.title = title;
      this.feeItemList = []
      this.visible = true;
      this.type = type;
      this.loadFeeItemByType()
    },
    //根据类型查询对应的收费项
    loadFeeItemByType() {
      this.hasIds = []
      getDefaultPayFeeItemByType(this.type).then(res => {
        this.feeItemList = res.data;
        this.feeItemList.filter(item => {
          this.hasIds.push(item.period);
        })
      })
    },
    dataFormSubmit(selectionList) {
      //取出当前选中的所有ID
      let ids=selectionList.map(item => item.id)
      addDefaultPayItemConfig(this.type, ids).then(res => {
        this.$message.success("保存成功");
        this.loadFeeItemByType()
      })
    },
    //打开收费项目选择的弹出层
    handleSelect() {
      this.showFeeConfig = true;
      this.$nextTick(() => {
        this.$refs.feeConfig.init(this.hasIds);
      });
    },
    //删除默认收费项目
    handleDel(row) {
      let tx=this;
      this.$modal.confirm('是否确认删除【"' + row.name + '"】的收费项？').then(function () {
        return delDefaultPayFeeItem(row.id,tx.type);
      }).then(() => {
        this.loadFeeItemByType();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    }
  },
};
</script>

