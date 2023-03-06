import request from '@/utils/request'

// 查询商圈管理列表
export function listBlock(query) {
  return request({
    url: '/asset/block/list',
    method: 'get',
    params: query
  })
}

// 查询商圈管理详细
export function getBlock(id) {
  return request({
    url: '/asset/block/' + id,
    method: 'get'
  })
}

// 新增商圈管理
export function addBlock(data) {
  return request({
    url: '/asset/block',
    method: 'post',
    data: data
  })
}

// 修改商圈管理
export function updateBlock(data) {
  return request({
    url: '/asset/block',
    method: 'put',
    data: data
  })
}

// 删除商圈管理
export function delBlock(id) {
  return request({
    url: '/asset/block/' + id,
    method: 'delete'
  })
}
//查询所有商圈不分页
export function getAllBlock() {
  return request({
    url: '/asset/block/getAllBlock',
    method: 'get'
  })
}
