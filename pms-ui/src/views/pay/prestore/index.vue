<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="客户姓名" prop="feeUserName">
        <el-input
          v-model="queryParams.feeUserName"
          placeholder="请输入客户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否指定收费项" prop="useFeeItem">
        <el-select v-model="queryParams.useFeeItem" placeholder="请选择是否指定收费项" clearable>
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="支付单号" prop="payNo">
        <el-input
          v-model="queryParams.payNo"
          placeholder="请输入支付单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >预存</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-back"
          size="mini"
          @click="handleRefund"
        >退费</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table border  v-loading="loading" :data="prestoreList">
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="收费项名" align="center" prop="feeItemName" />
      <el-table-column label="客户姓名" align="center" prop="feeUserName" />
      <el-table-column label="发生金额" align="center" prop="amt" />
      <el-table-column label="付款方式" align="center" prop="payType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fee_method_options" :value="scope.row.payType"/>
        </template>
      </el-table-column>
      <el-table-column label="支取还是收款" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fee_prestore_type_options" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="是否指定收费项" align="center" prop="useFeeItem">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.useFeeItem"/>
        </template>
      </el-table-column>
      <el-table-column label="操作人" align="center" prop="operateUser" />
      <el-table-column label="操作时间" align="center" prop="operateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="支付单号" align="center" prop="payNo" />
      <el-table-column label="操作" align="center" fiexd="right" width="200px" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.type=='pay'"
            icon="el-icon-print"
            @click="handlePrintAdd(scope.row)"
          >打印收款单</el-button>
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.type=='refund'"
            icon="el-icon-print"
            @click="handlePrintRefund(scope.row)"
          >打印退款单</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleViewOwner(scope.row)"
          >客户信息</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <prestore-add ref="prestoreAdd" @after="getList" v-if="openAdd"></prestore-add>
    <prestore-refund ref="prestoreRefund" @after="getList" v-if="openRefund"></prestore-refund>

    <user-detail ref="userDetail" v-if="showDetail"></user-detail>
  </div>
</template>

<script>
import { listPrestore } from "@/api/pay/prestore";
import prestoreAdd from "@/views/pay/prestore/prestoreAdd";
import prestoreRefund from "@/views/pay/prestore/prestoreRefund";
import userDetail from "@/views/owner/user/detail/index"
export default {
  name: "Prestore",
  components:{
    prestoreAdd,
    prestoreRefund,
    userDetail
  },
  dicts: ['fee_prestore_type_options', 'sys_yes_no', 'fee_method_options'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 费用预存表格数据
      prestoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示预存
      openAdd: false,
      // 是否显示退费
      openRefund: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        feeItemId: null,
        feeItemName: null,
        feeUserId: null,
        feeUserName: null,
        payType: null,
        type: null,
        useFeeItem: null,
        operateUser: null,
        operateTime: null,
        payNo: null,
      },
      //是否打开详情
      showDetail:false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询费用预存列表 */
    getList() {
      this.loading = true;
      listPrestore(this.queryParams).then(response => {
        this.prestoreList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    //打印收款单
    handlePrintAdd(row){

    },
    //打印退款单
    handlePrintRefund(row){

    },
    /** 新增按钮操作 */
    handleAdd() {
      this.openAdd = true;
      this.$nextTick(() => {
        this.$refs.prestoreAdd.init();
      });
    },
    /** 修改按钮操作 */
    handleRefund() {
      this.openRefund = true;
      this.$nextTick(() => {
        this.$refs.prestoreRefund.init();
      });
    },
    //客户信息
    handleViewOwner(row){
      this.showDetail = true;
      this.$nextTick(() => {
        this.$refs.userDetail.init(row.feeUserId);
      });
    }
  }
};
</script>
