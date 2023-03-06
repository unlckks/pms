import request from '@/utils/request'

// 查询业主信息列表
export function listUser(query) {
  return request({
    url: '/owner/user/list',
    method: 'get',
    params: query
  })
}

// 查询业主信息详细
export function getUser(id) {
  return request({
    url: '/owner/user/' + id,
    method: 'get'
  })
}

// 新增业主信息
export function addUser(data) {
  return request({
    url: '/owner/user',
    method: 'post',
    data: data
  })
}

// 修改业主信息
export function updateUser(data) {
  return request({
    url: '/owner/user',
    method: 'put',
    data: data
  })
}
