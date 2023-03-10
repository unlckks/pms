import request from '@/utils/request'

// 查询合同列表列表
export function listContract(query) {
  return request({
    url: '/pay/contract/listContract',
    method: 'get',
    params: query
  })
}
//添加合同
export function addContract(data) {
  return request({
    url: '/pay/contract/addContract',
    method: 'post',
    data: data
  })
}
//根据合同编号撤销合同
export function cancelContract(code){
  return request({
    url: '/pay/contract/cancelContract/'+code,
    method: 'get'
  })
}

// 查询可生成费用的合同列表
export function listBillContract(query) {
  return request({
    url: '/pay/contract/listBillContract',
    method: 'get',
    params: query
  })
}
// 根据合同编号查询当前合同所有收费项目
export function getContractFeeItemsByCode(code) {
  return request({
    url: '/pay/contract/getContractFeeItemsByCode/'+code,
    method: 'get'
  })
}



