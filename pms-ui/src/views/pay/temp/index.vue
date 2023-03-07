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
      <el-form-item label="收费项目" prop="feeItemId">
        <el-select v-model="queryParams.feeItemId" placeholder="请选择收费项目" clearable>
          <el-option
            v-for="item in feeItemList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="客户姓名" prop="feeUser">
        <el-input
          v-model="queryParams.feeUser"
          placeholder="请输入客户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="付款方式" prop="payType">
        <el-select v-model="queryParams.payType" placeholder="请选择付款方式" clearable>
          <el-option
            v-for="dict in dict.type.fee_method_options"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.fee_pay_type_options"
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
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['pay:temp:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pay:temp:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table border v-loading="loading" :data="tempList">
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="资源名" align="center" prop="resourceName" />
      <el-table-column label="收费项名" align="center" prop="feeItemName" />
      <el-table-column label="客户姓名" align="center" prop="feeUser" />
      <el-table-column label="金额" align="center" prop="amt" />
      <el-table-column label="付款方式" align="center" prop="payType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fee_method_options" :value="scope.row.payType"/>
        </template>
      </el-table-column>
      <el-table-column label="退费方式" align="center" prop="refundType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fee_method_options" :value="scope.row.refundType"/>
        </template>
      </el-table-column>
      <el-table-column label="收款人" align="center" prop="operateUser" />
      <el-table-column label="收款时间" align="center" prop="operateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column  label="退款人" align="center" prop="refundUser" />
      <el-table-column label="退款时间" align="center" prop="refundTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.refundTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="支付单号" align="center" prop="payNo" />
      <el-table-column label="退款单号" align="center" prop="refundNo" />
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fee_pay_type_options" :value="scope.row.state"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" fiexd="right" width="150px" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            :disabled="scope.row.state=='payed'"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >退款详情</el-button>
          <el-button
            size="mini"
            type="text"
            :disabled="scope.row.state=='refund'"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >退款</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >打印</el-button>
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

    <!-- 添加或修改临时收费对话框 -->
    <el-dialog :title="title" center  :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户类型" v-if="isRefund==false">
          <el-radio-group v-model="form.userType">
            <el-radio :label="1">商铺客户</el-radio>
            <el-radio :label="2">非商铺客户</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="商铺编号" prop="resourceName" v-if="form.userType==1&isRefund==false">
          <el-select v-model="form.resourceId" filterable placeholder="请选择">
            <el-option
              v-for="item in houseList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
              <span style="float: left">{{ item.name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.stateCompany }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收费项目" prop="feeItemId" v-if="isRefund==false">
          <el-select v-model="form.feeItemId" placeholder="请选择收费项目" clearable>
            <el-option
              v-for="item in feeItemList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="客户姓名" prop="feeUser" v-if="isRefund==false">
          <el-input v-model="form.feeUser" placeholder="请输入客户姓名"/>
        </el-form-item>
        <el-form-item label="金额" prop="amt" v-if="isRefund==false">
          <el-input v-model="form.amt" placeholder="请输入金额"/>
        </el-form-item>
        <el-form-item label="付款方式" prop="payType" v-if="isRefund==false">
          <el-select v-model="form.payType" placeholder="请选择付款方式">
            <el-option
              v-for="dict in dict.type.fee_method_options"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="退款方式" prop="refundType" v-if="isRefund==true">
          <el-select  v-model="form.refundType" placeholder="请选择付款方式">
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
      <div slot="footer" class="dialog-footer" >
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTemp, getTemp, addTemp, updateTemp } from "@/api/pay/temp";
import {getPayFeeItemByType} from "@/api/pay/payItemConfig";
import {getAllHouses} from "@/api/asset/house";

export default {
  name: "Temp",
  dicts: ['fee_method_options','fee_pay_type_options'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 临时收费表格数据
      tempList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        resourceName: null,
        resourceId: null,
        block: null,
        feeItemId: null,
        feeItemName: null,
        feeUser: null,
        amt: null,
        payType: null,
        refundType: null,
        operateUser: null,
        operateTime: null,
        refundUser: null,
        refundTime: null,
        payNo: null,
        refundNo: null,
        state: null,
      },
      // 表单参数
      form: {
        userType: "1"
      },
      // 表单校验
      rules: {
        feeItemId: [
          { required: true, message: "收费项目ID不能为空", trigger: "blur" }
        ],
        feeItemName: [
          { required: true, message: "收费项名不能为空", trigger: "blur" }
        ],
      },
      //收费项目
      feeItemList: [],
      //house列表
      houseList: [],
      //是否退款的弹出层
      isRefund:false
    };
  },
  created() {
    this.getList();
    this.loadPayFeeItemByType();
    //加载所有house
    this.loadAllHouses();
  },
  methods: {
    //加载所有house
    loadAllHouses() {
      getAllHouses().then(res => {
        this.houseList = res.data;
      })
    },
    //根据类型查询收费项目
    loadPayFeeItemByType() {
      getPayFeeItemByType('temp').then(res => {
        this.feeItemList = res.data;
      })
    },
    /** 查询临时收费列表 */
    getList() {
      this.loading = true;
      listTemp(this.queryParams).then(response => {
        this.tempList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userType: "1",
        id: null,
        resourceName: null,
        resourceId: null,
        block: null,
        feeItemId: null,
        feeItemName: null,
        feeUser: null,
        amt: null,
        payType: null,
        refundType: null,
        operateUser: null,
        operateTime: null,
        refundUser: null,
        refundTime: null,
        remark: null,
        payNo: null,
        refundNo: null,
        state: null,
      };
      this.resetForm("form");
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.userType=1
      this.open = true;
      this.title = "添加临时收费";
      this.isRefund=false;
    },
    /** 退款按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTemp(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = `给【${row.feeUser}的${row.resourceName}】退款`;
        this.isRefund=true
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if(this.form.id!=null){
            updateTemp(this.form).then(res=>{
              this.$modal.msgSuccess("退款成功");
              this.open = false;
              this.getList();
            })
          }else{
            //因为项目类型选择之后只有ID 给form里面加一个项目名称
            let feeItemName = "";
            this.feeItemList.filter(item => {
              if (item.id == this.form.feeItemId) {
                feeItemName = item.name;
              }
            })
            //赋值给form
            this.form.feeItemName = feeItemName;
            if (this.form.userType == 1) {
              let resourceName = "";
              let block = "";
              this.houseList.filter(item => {
                if (item.id == this.form.resourceId) {
                  resourceName = item.name;
                  block = item.blockId;
                }
              })
              this.form.resourceName = resourceName;
              this.form.block = block;
            }
            addTemp(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pay/temp/export', {
        ...this.queryParams
      }, `temp_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
