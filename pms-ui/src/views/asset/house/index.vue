<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="完整编号" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入完整编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商圈楼栋" prop="blockAndBuildingId">
        <el-cascader
          v-model="queryParams.blockAndBuildingId"
          :options="options"
          @change="handleChange"></el-cascader>
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
            :labbe="dict.label"
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
          v-hasPermi="['asset:house:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:house:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table border v-loading="loading" :data="houseList">
      <el-table-column label="主键" align="center" prop="id"/>
      <el-table-column label="房号" align="center" prop="code"/>
      <el-table-column label="完整编号" align="center" prop="name"/>
      <el-table-column label="商圈" align="center" prop="blockId">
        <template slot-scope="{row}">
          <div v-for="item in blockList" v-if="item.id==row.blockId">{{ item.name }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="楼栋" align="center" prop="buildingId">
        <template slot-scope="{row}">
          <div v-for="item in buildingList" v-if="item.id==row.buildingId">{{ item.name }}</div>
        </template>
      </el-table-column>
      <el-table-column label="楼层" align="center" prop="floor"/>
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.asset_house_state" :value="scope.row.state"/>
        </template>
      </el-table-column>
      <el-table-column label="占地面积(㎡)" align="center" prop="buildingSquare">
        <template slot-scope="{row}">
          {{ row.buildingSquare.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="使用面积(㎡)" align="center" prop="useSquare">
        <template slot-scope="{row}">
          {{ row.useSquare.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="租金(人民币/月)" align="center" prop="rentFee">
        <template slot-scope="{row}">
          {{ row.rentFee.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:house:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:house:remove']"
          >删除
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

    <!-- 添加或修改商铺管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="房号" prop="code">
          <el-input v-model="form.code" placeholder="请输入房号"/>
        </el-form-item>
        <el-form-item label="商圈楼栋" prop="blockAndBuildingId">
          <el-cascader
            style="width: 100%"
            v-model="form.blockAndBuildingId"
            :options="options"
            @change="handleFormChange">
          </el-cascader>
        </el-form-item>
        <el-form-item label="楼层" prop="floor">
          <el-input v-model="form.floor" placeholder="请输入楼层"/>
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-select
            v-model="form.state"
            placeholder="请选择状态"
            clearable
            disabled
            @keyup.enter.native="handleQuery"
            style="width: 100%"
          >
            <el-option
              v-for="dict in dict.type.asset_house_state"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="占地面积" prop="buildingSquare">
          <el-input v-model="form.buildingSquare" placeholder="请输入占地面积">
            <template slot="append">㎡</template>
          </el-input>
        </el-form-item>
        <el-form-item label="使用面积" prop="useSquare">
          <el-input v-model="form.useSquare" placeholder="请输入使用面积">
            <template slot="append">㎡</template>
          </el-input>
        </el-form-item>
        <el-form-item label="租金" prop="rentFee">
          <el-input v-model="form.rentFee" placeholder="请输入租金">
            <template slot="append">元/月</template>
          </el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" v-model="form.remark" placeholder="请输入备注"/>
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
import {listHouse, getHouse, delHouse, addHouse, updateHouse} from "@/api/asset/house";
//获取楼层
import {getAllBuilding} from "@/api/asset/building";
//获得商铺信息
import {getAllBlock} from "@/api/asset/houseBlock";

export default {
  name: "House",
  dicts: ['asset_house_state'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商铺管理表格数据
      houseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        blockId: null,
        buildingId: null,
        state: null,
        blockAndBuildingId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      //楼层
      blockList: [],
      //商铺
      buildingList: [],
      options: []
    };
  },
  created() {
    this.getList();
    //进行查询所有的商圈和楼栋
    this.loadAllBlockAndBuilding();
  },
  methods: {
    //进行查询所有的商圈和楼栋
    loadAllBlockAndBuilding() {
      //先获取商圈，再楼栋再进行组装
      getAllBlock().then(resBlock => {
        this.blockList = resBlock.data;
        //获取楼栋
        getAllBuilding().then(resBuilding => {
          this.buildingList = resBuilding.data;
          //进行组装
          this.options = this.handleBlockAndBuildingData(this.blockList, this.buildingList);
        })
      })
    },
    //进行查询条件的商圈和楼栋变化事件
    handleChange(val) {
      this.queryParams.blockId = val[0];
      this.queryParams.buildingId = val[1];
    },
    //进行处理添加修改弹出层商圈楼栋的数据变化
    handleFormChange(val) {
      this.form.blockId = val[0];
      this.form.buildingId = val[1];
    },
    /** 查询商铺管理列表 */
    getList() {
      this.loading = true;
      listHouse(this.queryParams).then(response => {
        this.houseList = response.rows;
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
        code: null,
        name: null,
        blockId: null,
        buildingId: null,
        floor: null,
        state: "empty",
        buildingSquare: null,
        useSquare: null,
        rentFee: null,
        remark: null,
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
      this.title = "添加商铺管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      getHouse(row.id).then(response => {
        this.form = response.data;
        this.form.blockAndBuildingId = [this.form.blockId, this.form.buildingId]
        this.open = true;
        this.title = "修改商铺管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      console.log(this.form)
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateHouse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHouse(this.form).then(response => {
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
      const id = row.id;
      this.$modal.confirm('是否确认删除商铺管理编号为"' + row.name + '"的数据项？').then(function () {
        return delHouse(id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('asset/house/export', {
        ...this.queryParams
      }, `house_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
