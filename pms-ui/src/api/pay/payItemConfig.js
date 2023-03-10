import request from '@/utils/request'

// 查询收费项目管理列表
export function listPayItemConfig(query) {
  return request({
    url: '/pay/payItemConfig/list',
    method: 'get',
    params: query
  })
}

// 查询收费项目管理详细
export function getPayItemConfig(id) {
  return request({
    url: '/pay/payItemConfig/' + id,
    method: 'get'
  })
}

// 新增收费项目管理
export function addPayItemConfig(data) {
  return request({
    url: '/pay/payItemConfig',
    method: 'post',
    data: data
  })
}

// 修改收费项目管理
export function updatePayItemConfig(data) {
  return request({
    url: '/pay/payItemConfig',
    method: 'put',
    data: data
  })
}

// 删除收费项目管理
export function delPayItemConfig(id) {
  return request({
    url: '/pay/payItemConfig/' + id,
    method: 'delete'
  })
}
//根据项目类型查询收费项目
export function getPayFeeItemByType(type) {
  return request({
    url: '/pay/payItemConfig/getPayFeeItemByType/'+type,
    method: 'get'
  })
}
//根据类型查询已配置默认收费项目
export function getDefaultPayFeeItemByType(type) {
  return request({
    url: '/pay/payItemConfig/getDefaultPayFeeItemByType/'+type,
    method: 'get'
  })
}

// 删除默认收费项目（根据收费项目ID和类型）
export function delDefaultPayFeeItem(id,type) {
  console.log(id,type)
  return request({
    url: '/pay/payItemConfig/delDefaultPayFeeItem/' + id+"/"+type,
    method: 'delete'
  })
}
// 新增默认收费项目管理
export function addDefaultPayItemConfig(type,ids) {
  let params="type="+type;//type=rented&ids=1&ids=2&ids=3
  ids.filter(id=>{
    params+="&ids="+id;
  })
  return request({
    url: '/pay/payItemConfig/addDefaultPayItemConfig?'+params,
    method: 'post'
  })
}
