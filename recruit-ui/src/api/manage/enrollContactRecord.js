import request from '@/utils/request'

// 查询考生联络记录列表
export function listEnrollContactRecord(query) {
  return request({
    url: '/manage/enrollContactRecord/list',
    method: 'get',
    params: query
  })
}

// 查询考生联络记录详细
export function getEnrollContactRecord(contactId) {
  return request({
    url: '/manage/enrollContactRecord/' + contactId,
    method: 'get'
  })
}

// 新增考生联络记录
export function addEnrollContactRecord(data) {
  return request({
    url: '/manage/enrollContactRecord',
    method: 'post',
    data: data
  })
}

// 修改考生联络记录
export function updateEnrollContactRecord(data) {
  return request({
    url: '/manage/enrollContactRecord',
    method: 'put',
    data: data
  })
}

// 删除考生联络记录
export function delEnrollContactRecord(contactId) {
  return request({
    url: '/manage/enrollContactRecord/' + contactId,
    method: 'delete'
  })
}
