import request from '@/utils/request'

// 查询停车场列表
export function listPark(query) {
  return request({
    url: '/park/park/list',
    method: 'get',
    params: query
  })
}

// 查询停车场详细
export function getPark(id) {
  return request({
    url: '/park/park/' + id,
    method: 'get'
  })
}

// 新增停车场
export function addPark(data) {
  return request({
    url: '/park/park',
    method: 'post',
    data: data
  })
}

// 修改停车场
export function updatePark(data) {
  return request({
    url: '/park/park/',
    method: 'put',
    data: data
  })
}

// 删除停车场
export function delPark(id) {
  return request({
    url: '/park/park/' + id,
    method: 'delete'
  })
}
//查询所有停车场不分页
export function getAllPark() {
  return request({
    url: '/park/park/getAllPark/',
    method: 'get'
  })
}
