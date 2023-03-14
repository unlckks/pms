import request from '@/utils/request'

//添加常规收费项目
export function addOrderNormal(code,ids) {
  let params="code="+code;
  ids.filter(id=>{
    params+="&ids="+id
  })
  return request({
    url: '/pay/order/addOrderNormal?'+params,
    method: 'get'
  })
}
// 查询合同列表列表
export function listOrder(query) {
  return request({
    url: '/pay/order/listOrder',
    method: 'get',
    params: query
  })
}

//根据商铺ID有关当前商铺的所有信息
export function getAllContentByHouseId(houseId) {
  return request({
    url: '/pay/order/getAllContentByHouseId/'+houseId,
    method: 'get'
  })
}
//创建支付的订单包含订单的详情
export function addPayTradeOrder(data) {
  return request({
    url: '/pay/order/addPayTradeOrder',
    method: 'post',
    data:data
  })
}
//根据主订单ID查询订单详情
export function queryTradeOrderItemByTradeId(tradeId) {
  return request({
    url: '/pay/order/queryTradeOrderItemByTradeId/'+tradeId,
    method: 'get'
  })
}
//创建一个支付宝的支付订单
export function createPayOrder(tradeId) {
  return request({
    url: '/pay/order/createPayOrder/'+tradeId,
    method: 'get'
  })
}
//根据支付订单ID检查订单支付状态
export function checkOrderStateTradeId(tradeId){
  return request({
    url: '/pay/order/checkOrderStateTradeId/'+tradeId,
    method: 'get'
  })
}

