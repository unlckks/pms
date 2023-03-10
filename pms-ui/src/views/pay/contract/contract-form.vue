<template>
  <div>
    <el-dialog title="合同信息" :close-on-click-modal="false" :visible.sync="contractFormVisible" width="600px">
      <el-steps :active="stepActive" align-center>
        <el-step title="步骤 1" description="确认商铺信息"></el-step>
        <el-step title="步骤 2" description="选择业主"></el-step>
        <el-step title="步骤 3" description="设置费用"></el-step>
      </el-steps>
      <div v-show="stepActive == 0">
        <el-descriptions class="margin-top" :column="2" border>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-goods"></i>
              商圈名
            </template>
            {{ houseAllContent.blockName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-menu"></i>
              楼栋名
            </template>
            {{ houseAllContent.buildingName }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-circle-check"></i>
              房号
            </template>
            {{ houseAllContent.code }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-operation"></i>
              楼层
            </template>
            {{ houseAllContent.floor }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-baseball"></i>
              完整门牌号
            </template>
            {{ houseAllContent.houseName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              商圈地址
            </template>
            {{ houseAllContent.address }}
          </el-descriptions-item>
        </el-descriptions>
      </div>
      <div style="margin-top: 5px">
        <el-form
          ref="elForm"
          :model="dataForm"
          :rules="rules"
          size="medium"
          label-width="100px"
          label-position="right"
          v-loading="loading"
        >
          <!--第一步开始-->
          <el-form-item label="类别" prop="contractType" v-show="stepActive == 0">
            <el-select
              v-model="dataForm.contractType"
              placeholder="类别"
              style="width: 100%"
            >
              <el-option
                v-for="dict in dict.type.pay_contract_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item v-show="stepActive == 0" label="开始时间" prop="beginDate" :style="{ height: '33px' }">
            <el-date-picker
              v-model="dataForm.beginDate"
              placeholder="请选择"
              clearable
              :style="{ width: '100%' }"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
            ></el-date-picker>
          </el-form-item>
          <el-form-item v-show="stepActive == 0" v-if="this.dataForm.contractType == 'rented'" label="出租时间"
                        prop="period"
                        :style="{ height: '33px' }">
            <el-input v-model="dataForm.period" placeholder="出租时间" clearable :style="{ width: '100%' }">
              <template slot="append">月</template>
            </el-input>
          </el-form-item>
          <el-form-item v-show="stepActive == 0" v-if="this.dataForm.contractType == 'rented'" label="租金" prop="rentFee"
                        :style="{ height: '33px' }">
            <el-input v-model="dataForm.rentFee" placeholder="租金" clearable :style="{ width: '100%' }">
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <!--第一步结束-->
          <!--第二步开始-->
          <el-form-item label="业主编号" prop="ownerId" v-show="stepActive == 1">
            <el-row>
              <el-col :span="16">
                <el-input
                  v-model="dataForm.ownerId"
                  placeholder="请输入"
                  :maxlength="20"
                  clearable
                  :style="{ width: '100%' }"
                  :disabled="true"
                ></el-input>
              </el-col>
              <el-col :span="8">
                <el-button type="primary" @click="selectOwner">选择业主</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <div v-show="stepActive == 1" style="margin-bottom: 5px">
            <el-descriptions class="margin-top" :column="2" border>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-s-goods"></i>
                  公司名称
                </template>
                {{ ownerUserContent.company }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-menu"></i>
                  联系人
                </template>
                {{ ownerUserContent.username }}
              </el-descriptions-item>

              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-circle-check"></i>
                  身份证号
                </template>
                {{ ownerUserContent.identity }}
              </el-descriptions-item>
            </el-descriptions>
          </div>
          <el-form-item label="从事的行业" v-show="stepActive == 1">
            <el-input
              v-model="dataForm.userTrade"
              placeholder="请输入"
              :maxlength="13"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
          <el-col :span="24" style="height: 59px" v-show="stepActive == 1">
            <el-form-item label="详细说明" prop="userTradeDetail">
              <el-input v-model="dataForm.userTradeDetail" :maxlength="150" placeholder="请输入" clearable
                        :style="{ width: '100%' }"></el-input>
            </el-form-item>
          </el-col>
          <!--第二步结束-->
          <!--第三步开始-->
          <div class="box" v-show="stepActive == 2" style="margin: 0 20px">
            <el-tabs v-model="activeName">
              <el-tab-pane label="设置收费项目" name="contractFees">
                <el-table :data="feeItemList" size="mini">
                  <el-table-column prop="id" label="项目ID" align="center"/>
                  <el-table-column prop="name" label="收费项名" align="center"/>
                  <el-table-column prop="price" label="单价" align="center"/>
                  <el-table-column label="操作" width="50">
                    <template slot-scope="scope">
                      <el-button size="mini" type="text" @click="handleDel(scope.$index, scope.row)">
                        删除
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="table-actions" @click="selectPayFeeItem" style="text-align: center">
                  <el-button type="text" icon="el-icon-plus">新增收费项</el-button>
                </div>
              </el-tab-pane>
            </el-tabs>
          </div>
          <!--第三步结束-->
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="prev" v-if="stepActive !== 0">上一步</el-button>
            <el-button @click="next" v-if="stepActive !== 2">下一步</el-button>
            <el-button type="primary" @click="dataFormSubmit()" v-if="stepActive == 2">确 定</el-button>
        </span>
    </el-dialog>
    <SelectOwnerBox v-if="selectOwnerBoxVisible" ref="selectOwner" @select="ownerSelected"/>
    <FeeSelectBox v-if="feeBoxVisible" ref="feeSelectBox" @submit="addFeeItem"/>
  </div>
</template>
<script>
import SelectOwnerBox from '@/views/owner/user/selectBox';
import FeeSelectBox from '@/views/pay/payItemConfig/select-box';
import {getAllResourceByHouseId} from "@/api/asset/house";
import {getDefaultPayFeeItemByType} from "@/api/pay/payItemConfig";
import {addContract} from "@/api/pay/contract"

export default {
  components: {FeeSelectBox, SelectOwnerBox},
  dicts: ['sys_user_sex', 'pay_contract_type'],
  data() {
    return {
      stepActive: 0,
      ownerType: '1',
      activeName: 'contractFees',
      loading: false,
      contractFormVisible: false,
      feeBoxVisible: false,
      selectOwnerBoxVisible: false,
      dataForm: {
        id: undefined,
        houseId: '',
        ownerId: '',
        contractFeeIds: [],
        resourceType: 'house',
        period: undefined,
        contractType: "",
        beginDate: undefined,
        endDate: undefined,
        userTrade: '',
        userTradeDetail: undefined,
        remark: undefined,
        rentFee: undefined,
      },
      rules: {
        contractType: [
          {
            required: true,
            message: '请选择',
            trigger: 'blur',
          },
        ],
        beginDate: [
          {
            required: true,
            message: '请选择',
            trigger: 'blur',
          },
        ],
        period: [
          {
            required: true,
            message: '请选择',
            trigger: 'blur',
          },
          {
            pattern: /^\d+$/,
            message: '请输入正确的数字',
            trigger: 'blur',
          },
        ],
        rentFee: [
          {
            required: true,
            message: '请输入',
            trigger: 'blur',
          },
          {
            pattern: /^(([1-9]{1}\d*)|(0{1}))(\.\d{0,2})?$/,
            message: '请输入正确的数字，最多两位小数',
            trigger: 'blur',
          },
          {
            pattern: /^(([1-9]{1}\d{0,7})|(0{1}))(\.\d{0,2})?$/,
            message: '小数点前最多8位数字',
            trigger: 'blur',
          },
        ]
      },
      //存放House的所有信息
      houseAllContent: {},
      //业主所有信息
      ownerUserContent: {},
      //默认收费项目
      feeItemList: []
    };
  },
  watch: {
    'dataForm.contractType': function () {
      getDefaultPayFeeItemByType(this.dataForm.contractType).then(res => {
        this.feeItemList = res.data;
      })
    }
  },
  methods: {
    init(id) {
      this.stepActive = 0;
      this.contractFormVisible = true;
      this.dataForm.houseId = id;
      getAllResourceByHouseId(id).then(res => {
        this.houseAllContent = res.data;
      })
    },
    //添加收费项目
    selectPayFeeItem() {
      this.feeBoxVisible = true;
      this.$nextTick(() => {
        this.$refs.feeSelectBox.init();
      });
    },
    //选择业主
    selectOwner() {
      this.selectOwnerBoxVisible = true;
      this.$nextTick(() => {
        this.$refs.selectOwner.init();
      });
    },
    //选择业主之后的回调
    ownerSelected(data) {
      this.ownerUserContent = data;
      this.dataForm.ownerId = data.id;
    },
    //下一步
    next() {
      this.$refs['elForm'].validate(valid => {
        if (valid) {
          this.stepActive++;
        }
      });
    },
    //上一步
    prev() {
      this.stepActive--;
    },
    //删除
    handleDel(index, row) {
      this.feeItemList.splice(index, 1);
    },
    //选择费用之后回调
    addFeeItem(feeList) {
      console.log(feeList)
      if (feeList != undefined && feeList.length > 0) {
        feeList.filter(item => {
          this.feeItemList.push(item);
        })
      }
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs['elForm'].validate(valid => {
        if (valid) {
          this.dataForm.contractFeeIds = []
          this.feeItemList.filter(item => {
            this.dataForm.contractFeeIds.push(item.id)
          })
          console.log(this.dataForm)
          //添加合同
          addContract(this.dataForm).then(res=>{
            this.$message.success("添加成功")
            this.contractFormVisible=false;
            this.$emit("after")
          })
        }
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.stepRow {
  margin-top: 20px;
  margin-right: 60px !important;
}
</style>
