import request from '@/utils/request'

// 查询支付订单详情列表
export function listItem(query) {
  return request({
    url: '/pay/item/list',
    method: 'get',
    params: query
  })
}

// 查询支付订单详情详细
export function getItem(orderId) {
  return request({
    url: '/pay/item/' + orderId,
    method: 'get'
  })
}

// 新增支付订单详情
export function addItem(data) {
  return request({
    url: '/pay/item',
    method: 'post',
    data: data
  })
}

// 修改支付订单详情
export function updateItem(data) {
  return request({
    url: '/pay/item',
    method: 'put',
    data: data
  })
}

// 删除支付订单详情
export function delItem(orderId) {
  return request({
    url: '/pay/item/' + orderId,
    method: 'delete'
  })
}
