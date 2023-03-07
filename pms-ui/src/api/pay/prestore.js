import request from '@/utils/request'

// 查询费用预存列表
export function listPrestore(query) {
  return request({
    url: '/pay/prestore/list',
    method: 'get',
    params: query
  })
}

// 查询费用预存详细
export function getPrestore(id) {
  return request({
    url: '/pay/prestore/' + id,
    method: 'get'
  })
}

// 新增费用预存
export function addPrestore(data) {
  return request({
    url: '/pay/prestore',
    method: 'post',
    data: data
  })
}

// 修改费用预存
export function updatePrestore(data) {
  return request({
    url: '/pay/prestore',
    method: 'put',
    data: data
  })
}

// 删除费用预存
export function delPrestore(id) {
  return request({
    url: '/pay/prestore/' + id,
    method: 'delete'
  })
}
//根据业主ID查询当前业主的所有预存款
export function getPayPrestoreAccountByUserOwnerId(id) {
  return request({
    url: '/pay/prestore/getPayPrestoreAccountByUserOwnerId/' + id,
    method: 'get'
  })
}

//退还预存款
export  function refundPrestoreAccount(data,type){
  return request({
    url: '/pay/prestore/refundPrestoreAccount/'+type,
    method: 'post',
    data:data
  })
}
