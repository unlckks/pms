import request from '@/utils/request'

// 查询道闸管理列表
export function listCheckpoint(query) {
  return request({
    url: '/park/checkpoint/list',
    method: 'get',
    params: query
  })
}

// 查询道闸管理详细
export function getCheckpoint(id) {
  return request({
    url: '/park/checkpoint/' + id,
    method: 'get'
  })
}

// 新增道闸管理
export function addCheckpoint(data) {
  return request({
    url: '/park/checkpoint',
    method: 'post',
    data: data
  })
}

// 修改道闸管理
export function updateCheckpoint(data) {
  return request({
    url: '/park/checkpoint',
    method: 'put',
    data: data
  })
}

// 删除道闸管理
export function delCheckpoint(id) {
  return request({
    url: '/park/checkpoint/' + id,
    method: 'delete'
  })
}
