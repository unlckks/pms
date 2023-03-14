<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="支付方式" prop="wayCode">
        <el-select v-model="queryParams.wayCode" placeholder="请选择付款方式" clearable>
          <el-option
            v-for="dict in dict.type.fee_method_options"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="支付状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择支付状态" clearable>
          <el-option
            v-for="dict in dict.type.pay_order_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商品标题" prop="subject">
        <el-input
          v-model="queryParams.subject"
          placeholder="请输入商品标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付时间" prop="successTime">
        <el-date-picker
          v-model="queryParams.payTimeRange"
          type="datetimerange"
          :picker-options="pickerOptions"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd HH:mm:ss"
          align="right"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table border v-loading="loading" :data="orderList">
      <el-table-column label="支付订单号" align="center" prop="id" width=""  />
      <el-table-column label="支付方式" align="center" prop="wayCode" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fee_method_options" :value="scope.row.wayCode"/>
        </template>
      </el-table-column>
      <el-table-column label="支付金额" align="center" prop="amount" />
      <el-table-column label="支付状态" align="center" prop="state" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pay_order_state" :value="scope.row.state"/>
        </template>
      </el-table-column>
      <el-table-column label="标题"  :show-overflow-tooltip="true" align="center" prop="subject" />
      <el-table-column label="描述信息"  :show-overflow-tooltip="true" align="center" prop="body" />
      <el-table-column label="渠道标识" align="center" prop="openId" />
      <el-table-column label="渠道订单ID" align="center" prop="tradeNo" />
      <el-table-column label="错误码" align="center" prop="errCode" />
      <el-table-column label="错误描述" align="center" prop="errMsg" />
      <el-table-column label="支付时间" align="center" prop="successTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.successTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-bank-card"
            v-if="scope.row.wayCode=='aliPay'&&scope.row.state!='2'"
            @click="handlePay(scope.row)"
          >支付宝支付</el-button>
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
    <trade-order-item ref="tradeOrderItem" v-if="showTradeOrderItem"></trade-order-item>
    <trade-order-pay ref="tradeOrderPay" v-if="showTradeOrderPay" @select="getList"></trade-order-pay>
  </div>
</template>

<script>
import { listOrder} from "@/api/pay/order";
import tradeOrderItem from './trade-order-item'
import tradeOrderPay from "@/views/pay/tradeOrder/trade-order-pay";

export default {
  name: "Order",
  components:{
    tradeOrderPay,
    tradeOrderItem
  },
  dicts:['fee_method_options','pay_order_state'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 支付订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wayCode: null,
        state: null,
        subject: null,
        body: null,
        tradeNo: null,
        payTimeRange:undefined
      },
      pickerOptions: {
        shortcuts: [
          {
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            },
          },
          {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            },
          },
          {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            },
          },
        ],
      },
      //是否显示详情
      showTradeOrderItem:false,
      //是否显示支付的二维码
      showTradeOrderPay:false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询支付订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
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
    /** 查看详情按钮操作 */
    handleView(row) {
      this.showTradeOrderItem=true;
      this.$nextTick(() => {
        this.$refs.tradeOrderItem.init(row.id);
      });
    },
    //打开支付的二维码
    handlePay(row){
      this.showTradeOrderPay=true;
      this.$nextTick(() => {
        this.$refs.tradeOrderPay.init(row.id);
      });
    }
  }
};
</script>
