import request from '@/utils/request'

// 查询楼栋管理列表
export function listBuilding(query) {
  return request({
    url: '/asset/building/list',
    method: 'get',
    params: query
  })
}

// 查询楼栋管理详细
export function getBuilding(id) {
  return request({
    url: '/asset/building/' + id,
    method: 'get'
  })
}

// 新增楼栋管理
export function addBuilding(data) {
  return request({
    url: '/asset/building',
    method: 'post',
    data: data
  })
}

// 修改楼栋管理
export function updateBuilding(data) {
  return request({
    url: '/asset/building',
    method: 'put',
    data: data
  })
}

// 删除楼栋管理
export function delBuilding(id) {
  return request({
    url: '/asset/building/' + id,
    method: 'delete'
  })
}
