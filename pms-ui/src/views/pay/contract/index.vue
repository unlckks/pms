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
      <el-form-item label="状态" prop="state">
        <el-select
          v-model="queryParams.state"
          placeholder="状态"
          clearable
          @keyup.enter.native="handleQuery"
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.asset_house_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          icon="el-icon-setting"
          size="mini"
          @click="handleSettingRented"
        >默认收费项目(出租)
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-setting"
          size="mini"
          @click="handleSettingSale"
        >默认收费项目(出售)
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table border v-loading="loading" :data="contractList">
      <el-table-column label="序号" align="center" type="index"/>
      <el-table-column label="商铺名" align="center" prop="resourceName" width="100"/>
      <el-table-column label="租售状态" align="center" prop="state" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.asset_house_state" :value="scope.row.state"/>
        </template>
      </el-table-column>
      <el-table-column label="合同状态" align="center" prop="contractState" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pay_contract_state" :value="scope.row.contractState"/>
        </template>
      </el-table-column>
      <el-table-column label="公司名" align="center" prop="company" width="100"/>
      <el-table-column label="合同编号" align="center" prop="code" width="100"/>
      <el-table-column label="业主姓名" align="center" prop="ownerName" width="100"/>
      <el-table-column label="电话" align="center" prop="phone" width="100"/>
      <el-table-column label="租金" align="center" prop="rentFee" width="100"/>
      <el-table-column label="行业" align="center" prop="userTrade" width="100"/>
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
      <el-table-column label="操作" width="250px" fixed="right" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            :disabled="scope.row.state != 'empty'"
            @click="handleAddContract(scope.row)"
          >发起合同
          </el-button>
          <el-button
            size="mini"
            type="text"
            :disabled="scope.row.state == 'empty'||scope.row.contractState!='1'"
            icon="el-icon-edit"
            @click="handleCancelContract(scope.row)"
          >撤销合同
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
    <!--收费项的弹出层-->
    <fee-config v-if="showFeeConfig" ref="feeConfig"></fee-config>
    <!--发起-编辑合同-->
    <contract-form v-if="showContractForm" ref="contractForm" @after="getList"></contract-form>
  </div>
</template>

<script>
import {listContract,cancelContract} from "@/api/pay/contract";
import feeConfig from "../payItemConfig/fee-config"
import contractForm from "@/views/pay/contract/contract-form"

export default {
  name: "Contract",
  dicts: ['asset_house_state','pay_contract_type','pay_contract_state'],
  components: {
    feeConfig,
    contractForm
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
      showContractForm:false,
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
      listContract(this.queryParams).then(response => {
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
    /** 默认收费项目(出租) */
    handleSettingRented() {
      this.showFeeConfig = true;
      this.$nextTick(() => {
        this.$refs.feeConfig.init('rented', '设置默认收费项目(出租)');
      });
    },
    /** 默认收费项目(出售) */
    handleSettingSale() {
      this.showFeeConfig = true;
      this.$nextTick(() => {
        this.$refs.feeConfig.init('sale', '设置默认收费项目(出售) ');
      });
    },
    /** 发起合同 */
    handleAddContract(row) {
      this.showContractForm = true;
      this.$nextTick(() => {
        //id为houseId
        this.$refs.contractForm.init(row.resourceId);
      });
    },
    /** 撤销合同 */
    handleCancelContract(row) {
      this.$modal.confirm('是否确认撤销合同编号为"' + row.code + '"的合同吗？').then(function() {
        return cancelContract(row.code);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("撤销成功");
      }).catch(() => {});
    }
  }
};
</script>
