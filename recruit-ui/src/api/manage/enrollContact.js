import request from '@/utils/request'

// 查询考生联络列表
export function listEnrollContact(query) {
  return request({
    url: '/manage/enrollContact/list',
    method: 'get',
    params: query
  })
}

// 查询考生联络详细
export function getEnrollContact(stuEnrollId) {
  return request({
    url: '/manage/enrollContact/' + stuEnrollId,
    method: 'get'
  })
}

// 新增考生联络
export function addEnrollContact(data) {
  return request({
    url: '/manage/enrollContact',
    method: 'post',
    data: data
  })
}

// 修改考生联络
export function updateEnrollContact(data) {
  return request({
    url: '/manage/enrollContact',
    method: 'put',
    data: data
  })
}

// 删除考生联络
export function delEnrollContact(stuEnrollId) {
  return request({
    url: '/manage/enrollContact/' + stuEnrollId,
    method: 'delete'
  })
}
