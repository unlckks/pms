<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商铺编号" prop="resourceName">
        <el-input
          v-model="queryParams.resourceName"
          placeholder="请输入商铺编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户姓名" prop="ownerName">
        <el-input
          v-model="queryParams.ownerName"
          placeholder="请输入客户姓名"
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table border v-loading="loading" :data="contractList">
      <el-table-column label="序号" align="center" type="index"/>
      <el-table-column label="商铺名" align="center" prop="resourceName" width="100"/>
      <el-table-column label="状态" align="center" prop="state" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.asset_house_state" :value="scope.row.state"/>
        </template>
      </el-table-column>
      <el-table-column label="公司名" align="center" prop="company" width="150"/>
      <el-table-column label="业主姓名" align="center" prop="ownerName" width="120"/>
      <el-table-column label="电话" align="center" prop="phone" width="180"/>
      <el-table-column label="租金" align="center" prop="rentFee" width="180"/>
      <el-table-column label="行业" align="center" prop="userTrade" width="180"/>

      <el-table-column label="合同类型" align="center" prop="contractType" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pay_contract_type" :value="scope.row.contractType"/>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="beginDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beginDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="有效时间" align="center" prop="period" width="180">
      </el-table-column>
      <el-table-column label="操作" width="150px" fixed="right" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleAddContractBill(scope.row)"
          >生成费用订单
          </el-button>
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
    <!--费用生成-->
    <bill-form v-if="showContractBillForm" ref="contractBillForm" @after="getList"></bill-form>
  </div>
</template>

<script>
import {listBillContract} from "@/api/pay/contract";
import billForm from "@/views/pay/bill/bill-form"

export default {
  name: "Contract",
  dicts: ['asset_house_state','pay_contract_type'],
  components: {
    billForm
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 押金表格数据
      contractList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      showFeeConfig: false,
      //是否显示添加和修改合同的弹出层
      showContractBillForm:false,
      showContractRentBillForm:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        resourceName: null,
        ownerName: null,
        state: null,
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询押金列表 */
    getList() {
      this.loading = true;
      listBillContract(this.queryParams).then(response => {
        this.contractList = response.rows;
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
    /** 生成费用 */
    handleAddContractBill(row) {
      this.showContractBillForm = true;
      this.$nextTick(() => {
        this.$refs.contractBillForm.init(row.resourceId,row.ownerId,row.code);
      });
    },
    /** 生成费用 */
    handleAddContractRentBill(row) {
      this.showContractRentBillForm = true;
      this.$nextTick(() => {
        this.$refs.contractRentBillForm.init(row.resourceId,row.ownerId,row.code);
      });
    }
  }
};
</script>
