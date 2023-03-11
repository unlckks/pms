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
    url: '/pay/order/listContract',
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


