<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择项目类型" clearable>
          <el-option
            v-for="dict in dict.type.fee_type_options"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="项目名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入收费项目名"
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
          v-hasPermi="['pay:payItemConfig:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pay:payItemConfig:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table border v-loading="loading" :data="payItemConfigList">
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="收费项分类"  width="150" align="center" prop="type" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fee_type_options" :value="scope.row.type"/>
        </template>
      </el-table-column>
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
      <el-table-column label="操作" align="center" width="150" fixed="right" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pay:payItemConfig:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pay:payItemConfig:remove']"
          >删除</el-button>
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

    <!-- 添加或修改收费项目管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="40%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="项目类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择项目类型" clearable>
            <el-option
              v-for="dict in dict.type.fee_type_options"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="收费项目名" prop="name">
          <el-input v-model="form.name" placeholder="请输入收费项目名" />
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input v-model="form.price" placeholder="请输入单价" />
        </el-form-item>
        <el-form-item label="数量类型" prop="numType">
          <el-select v-model="form.numType" placeholder="请选择数量类型">
            <el-option
              v-for="dict in dict.type.fee_num_type_options"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="费用公式" prop="formula" v-if="form.type=='house'">
          <el-select v-model="form.formula" placeholder="请选择费用公式">
            <el-option
              v-for="dict in dict.type.fee_formula_options"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="自定义" prop="formulaExpression" v-if="form.type=='house'&form.formula=='expression'">
          <el-input  type="textarea" v-model="form.formulaExpression" placeholder="请输入公式[电费为例子](n<=230?(0.5283*n):n<=400?(230*0.5283+(n-230)*0.5783)):(230*0.5283+170*0.5783+(n-400)*0.8783)"></el-input>
        </el-form-item>
        <el-form-item label="费用周期" prop="period" v-if="form.type=='house'">
          <el-select v-model="form.period" placeholder="请选择费用周期">
            <el-option
              v-for="dict in dict.type.fee_period_options"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否产生滞纳金" prop="lateFeeEnable" v-if="form.type=='house'">
          <el-select v-model="form.lateFeeEnable" placeholder="请选择是否产生滞纳金">
            <el-option
              v-for="dict in dict.type.fee_late_enable_options"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="滞纳金延迟天数" prop="lateFeeDays" v-if="form.type=='house'&form.lateFeeEnable=='Y'">
          <el-input v-model="form.lateFeeDays" placeholder="请输入滞纳金延迟多久收" />
        </el-form-item>
        <el-form-item label="滞纳金比例" prop="lateFeeRate" v-if="form.type=='house'&form.lateFeeEnable=='Y'">
          <el-input v-model="form.lateFeeRate" placeholder="请输入滞纳金比例" >
            <template slot="append">
              %
            </template>
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPayItemConfig, getPayItemConfig, delPayItemConfig, addPayItemConfig, updatePayItemConfig } from "@/api/pay/payItemConfig";

export default {
  name: "PayItemConfig",
  dicts: ['fee_type_options','fee_num_type_options', 'fee_formula_options', 'fee_period_options', 'fee_late_enable_options'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 收费项目管理表格数据
      payItemConfigList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        name: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "收费项分类不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "收费项目名不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "单价不能为空", trigger: "blur" }
        ],
        numType: [
          { required: true, message: "数量类型不能为空", trigger: "change" }
        ],
        period: [
          { required: true, message: "费用周期不能为空", trigger: "change" }
        ],
        formula: [
          { required: true, message: "费用公式不能为空", trigger: "change" }
        ],
        lateFeeEnable: [
          { required: true, message: "是否产生滞纳金不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询收费项目管理列表 */
    getList() {
      this.loading = true;
      listPayItemConfig(this.queryParams).then(response => {
        this.payItemConfigList = response.rows;
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
        id: null,
        type: null,
        name: null,
        price: null,
        numType: null,
        period: null,
        formula: null,
        formulaExpression: null,
        lateFeeEnable: null,
        lateFeeDays: null,
        lateFeeRate: null,
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
      this.open = true;
      this.title = "添加收费项目管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id
      getPayItemConfig(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改收费项目管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePayItemConfig(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPayItemConfig(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id;
      this.$modal.confirm('是否确认删除收费项目管理编号为"' + ids + '"的数据项？').then(function() {
        return delPayItemConfig(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pay/payItemConfig/export', {
        ...this.queryParams
      }, `payItemConfig_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
