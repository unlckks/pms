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
