import request from '@/utils/request'

// 查询押金列表
export function listDeposit(query) {
  return request({
    url: '/pay/deposit/list',
    method: 'get',
    params: query
  })
}

// 查询押金详细
export function getDeposit(id) {
  return request({
    url: '/pay/deposit/' + id,
    method: 'get'
  })
}

// 新增押金
export function addDeposit(data) {
  return request({
    url: '/pay/deposit',
    method: 'post',
    data: data
  })
}

// 修改押金
export function updateDeposit(data) {
  return request({
    url: '/pay/deposit',
    method: 'put',
    data: data
  })
}

// 删除押金
export function delDeposit(id) {
  return request({
    url: '/pay/deposit/' + id,
    method: 'delete'
  })
}

