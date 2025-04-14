import request from '@/utils/request'

// 查询录取信息列表
export function listEnrollBasic(query) {
  return request({
    url: '/manage/enrollBasic/list',
    method: 'get',
    params: query
  })
}

// 查询录取信息详细
export function getEnrollBasic(stuEnrollId) {
  return request({
    url: '/manage/enrollBasic/' + stuEnrollId,
    method: 'get'
  })
}

// 新增录取信息
export function addEnrollBasic(data) {
  return request({
    url: '/manage/enrollBasic',
    method: 'post',
    data: data
  })
}

// 修改录取信息
export function updateEnrollBasic(data) {
  return request({
    url: '/manage/enrollBasic',
    method: 'put',
    data: data
  })
}

// 删除录取信息
export function delEnrollBasic(stuEnrollId) {
  return request({
    url: '/manage/enrollBasic/' + stuEnrollId,
    method: 'delete'
  })
}
