import request from '@/utils/request'

// 查询临时收费列表
export function listTemp(query) {
  return request({
    url: '/pay/temp/list',
    method: 'get',
    params: query
  })
}

// 查询临时收费详细
export function getTemp(id) {
  return request({
    url: '/pay/temp/' + id,
    method: 'get'
  })
}

// 新增临时收费
export function addTemp(data) {
  return request({
    url: '/pay/temp',
    method: 'post',
    data: data
  })
}

// 退款
export function updateTemp(data) {
  return request({
    url: '/pay/temp',
    method: 'put',
    data: data
  })
}

// 删除临时收费
export function delTemp(id) {
  return request({
    url: '/pay/temp/' + id,
    method: 'delete'
  })
}
