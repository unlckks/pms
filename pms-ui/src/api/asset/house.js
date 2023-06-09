import request from '@/utils/request'

// 查询商铺管理列表
export function listHouse(query) {
  return request({
    url: '/asset/house/list',
    method: 'get',
    params: query
  })
}

// 查询商铺管理详细
export function getHouse(id) {
  return request({
    url: '/asset/house/' + id,
    method: 'get'
  })
}

// 新增商铺管理
export function addHouse(data) {
  return request({
    url: '/asset/house',
    method: 'post',
    data: data
  })
}

// 修改商铺管理
export function updateHouse(data) {
  return request({
    url: '/asset/house',
    method: 'put',
    data: data
  })
}

// 删除商铺管理
export function delHouse(id) {
  return request({
    url: '/asset/house/' + id,
    method: 'delete'
  })
}
//根据资源名查询资源列表
export function getAllHouses() {
  return request({
    url: '/asset/house/getAllHouses',
    method: 'get'
  })
}
//根据houseId名查询资源所有信息
export function getAllResourceByHouseId(houseId) {
  return request({
    url: '/asset/house/getAllResourceByHouseId/'+houseId,
    method: 'get'
  })
}
// 根据合同编号查询当前合同所有收费项目
export function getContractFeeItemsByCode(code) {
  return request({
    url: '/pay/contract/getContractFeeItemsByCode/'+code,
    method: 'get'
  })
}
