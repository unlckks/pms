<template>
  <div>
    <el-dialog title="常规费用信息生成" :close-on-click-modal="false" :visible.sync="contractFormVisible" width="800px">
      <div>
        <el-descriptions title="商铺信息" :column="2" border>
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
        <el-descriptions title="业主信息" :column="2" border>
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
      <div style="margin-top: 5px">
        <el-tabs v-model="activeName">
          <el-tab-pane label="收费项目" name="contractFees">
            <el-table :data="feeItemList" size="mini" border>
              <el-table-column prop="id" label="项目ID" align="center"/>
              <el-table-column prop="name" label="收费项名" align="center"/>
              <el-table-column label="操作" width="60" align="center">
                <template slot-scope="scope">
                  <el-button size="mini" type="text" @click="handleDel(scope.$index, scope.row)">
                    删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button :disabled="disable" type="primary" @click="dataFormSubmit()">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>
<script>
import {getAllResourceByHouseId} from "@/api/asset/house";
import {getUser} from "@/api/owner/user";
import {getContractFeeItemsByCode} from "@/api/pay/contract";
import {addOrderNormal} from "@/api/pay/order";

export default {
  dicts: ['pay_contract_type'],
  data() {
    return {
      ownerType: '1',
      activeName: 'contractFees',
      loading: false,
      contractFormVisible: false,
      feeBoxVisible: false,
      dataForm: {
        houseId: '',
        ownerId: '',
        contractFeeIds: [],
        code:""
      },
      rules: {},
      //存放House的所有信息
      houseAllContent: {},
      //业主所有信息
      ownerUserContent: {},
      //默认收费项目
      feeItemList: [],
      //禁用
      disable:false
    }
  },
  methods: {
    init(houseId,ownerId,code) {
      this.disable=false
      this.contractFormVisible = true;
      this.dataForm.houseId = houseId;
      this.dataForm.ownerId = ownerId;
      this.dataForm.code=code
      getAllResourceByHouseId(houseId).then(res => {
        this.houseAllContent = res.data;
      }).catch(()=>{
        this.disable=true
        this.contractFormVisible=false;
      })
      getUser(ownerId).then(res=>{
        this.ownerUserContent=res.data;
      }).catch(()=>{
        this.disable=true
        this.contractFormVisible=false;
      })
      getContractFeeItemsByCode(code).then(res=>{
        this.feeItemList=res.data;
      }).catch(()=>{
        this.disable=true
        this.contractFormVisible=false;
      })
    },
    //删除
    handleDel(index, row) {
      this.feeItemList.splice(index, 1);
    },
    // 表单提交
    dataFormSubmit() {
      this.disable=true;
      let ids=new Array();
      this.feeItemList.filter(item=>{
        ids.push(item.id);
      })
      addOrderNormal(this.dataForm.code,ids).then(res=>{
        this.disable=false;
        this.$message.success("添加成功")
        this.contractFormVisible=false
        this.$emit("after");
      })
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
