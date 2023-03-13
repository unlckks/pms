<template>
  <div class="app-container">
    <!--查询条件-->
    <el-card>
      <el-cascader
        :options="options"
        @change="handleChange"></el-cascader>
      <el-button style="margin-left: 5px" type="primary" icon="el-icon-search" @click="search()">查询</el-button>
    </el-card>
    <el-card style="margin-top: 5px" v-show="showContent">
      <el-descriptions title="商铺信息" :column="3" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-goods"></i>
            商圈名
          </template>
          {{ blockContent.name }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-menu"></i>
            楼栋名
          </template>
          {{ buildingContent.name }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            房号
          </template>
          {{ houseContent.code }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-operation"></i>
            楼层
          </template>
          {{ houseContent.floor }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-baseball"></i>
            完整门牌号
          </template>
          {{ houseContent.name }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>
            商圈地址
          </template>
          {{ blockContent.address }}
        </el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="业主信息" style="margin-top: 5px" :column="3" border>
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
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            联系电话
          </template>
          {{ ownerUserContent.phone }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            所租单元数量
          </template>
          {{ ownerUserContent.rentedCount }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            所购单元数量
          </template>
          {{ ownerUserContent.ownCount }}
        </el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="合同信息" style="margin-top: 5px" :column="3" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-goods"></i>
            合同编号
          </template>
          {{ contractContent.code }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-menu"></i>
            合同状态
          </template>
          <dict-tag :options="dict.type.pay_contract_state" :value="contractContent.state"/>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            有效时间
          </template>
          {{ contractContent.period }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            开始时间
          </template>
          {{ contractContent.beginDate }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            结束时间
          </template>
          {{ contractContent.endDate }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            租金
          </template>
          {{ contractContent.rentFee }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            所属行业
          </template>
          {{ contractContent.userTrade }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            行业详情
          </template>
          {{ contractContent.userTradeDetail }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-circle-check"></i>
            合同类型
          </template>
          <dict-tag :options="dict.type.pay_contract_type" :value="contractContent.contractType"/>
        </el-descriptions-item>
      </el-descriptions>
    </el-card>
    <el-card v-show="showContent">
      <el-button
        type="primary"
        plain
        icon="el-icon-plus"
        @click="handlePayBill"
        style="margin-bottom: 5px"
      >收费</el-button>
      <el-table border  show-summary  center :data="payOrderList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="itemName" label="收费项名称" align="center" />
        <el-table-column prop="beginTime" label="起收日期" align="center" />
        <el-table-column prop="endTime" label="结束日期" align="center" />
        <el-table-column prop="endTime" label="缴费限期" align="center" />
        <el-table-column prop="price" label="单价" align="center" />
        <el-table-column prop="num" label="数量"algin="center" />
        <el-table-column prop="amt" label="金额" algin="center" />
      </el-table>
    </el-card>
    <pay-bill @select="search" ref="payBill" v-if="payBillVisible"></pay-bill>
  </div>
</template>

<script>
import { getAllBlock } from '@/api/asset/houseBlock'
import { getAllBuilding } from '@/api/asset/building'
import { getAllHouses } from '@/api/asset/house'
import { getAllContentByHouseId } from '@/api/pay/order'
import payBill from "./pay-bill"

export default {
  dicts: ['pay_contract_type','pay_contract_state'],
  components:{
    payBill
  },
  data() {
    return {
      queryParams:{
        houseId:undefined,
      },
      //装商圈的属性
      blockList:[],
      //装楼栋的属性
      buildingList:[],
      //装商铺数据
      houseList:[],
      //级联选择器要的数据
      options:[],
      //存放商圈的所有信息
      blockContent: {},
      //存放楼栋的所有信息
      buildingContent: {},
      //存放商铺的所有信息
      houseContent: {},
      //业主所有信息
      ownerUserContent: {},
      //合同信息
      contractContent: {},
      //收费订单
      payOrderList:[],
      //是否至少有一个选中
      disabled:false,
      //是否显示
      showContent:false,
      //选中的收费项目
      multipleSelection:[],
      //是否显示收费的弹出层
      payBillVisible:false
    }
  },
  computed: {},
  created() {
    //先商圈再楼栋再组装
    getAllBlock().then(resBlock=>{
      this.blockList=resBlock.data;
      getAllBuilding().then(resBuilding=>{
        this.buildingList=resBuilding.data;
        getAllHouses().then(resHouse=>{
          this.houseList=resHouse.data;
          //组装数据 //TODO  handleBlockAndBuildingData  这个方法我改过
          this.options=this.handleBlockAndBuildingData(this.blockList,this.buildingList,this.houseList);
          console.log(this.options)
        })
      })
    })
  },
  methods: {
    //查询条件的商圈和楼栋变化事件
    handleChange(val){
      this.queryParams.houseId=val[2];
    },
    search(){
      getAllContentByHouseId(this.queryParams.houseId).then(res=>{
        console.log(res.data)
        //存放商圈的所有信息
        this.blockContent=res.data.blockContent;
        //存放楼栋的所有信息
        this.buildingContent=res.data.buildingContent;
        //存放商铺的所有信息
        this.houseContent=res.data.houseContent;
        //业主所有信息
        this.ownerUserContent=res.data.ownerUserContent;
        //合同信息
        this.contractContent=res.data.contractContent;
        //收费订单
        this.payOrderList=res.data.payOrderList;
        this.showContent=true;
      }).catch(()=>{
        this.showContent=false;
      })
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.disabled = !selection.length
      this.multipleSelection=selection
    },
    //打开收费的弹出层
    handlePayBill(){
      if (this.multipleSelection.length > 0) {
        this.payBillVisible = true;
        this.$nextTick(() => {
          this.$refs.payBill.init(this.multipleSelection,this.houseContent.id);
        });
      } else {
        this.$message({
          type: 'warning',
          message: '请选择要缴费的数据',
          duration: 1000,
        });
      }
    }
  }
}
</script>
