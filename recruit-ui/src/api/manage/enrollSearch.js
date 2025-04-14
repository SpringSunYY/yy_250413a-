import request from '@/utils/request'

// 查询考生查询列表
export function listEnrollSearch(query) {
  return request({
    url: '/manage/enrollSearch/list',
    method: 'get',
    params: query
  })
}

// 查询考生查询详细
export function getEnrollSearch(stuEnrollId) {
  return request({
    url: '/manage/enrollSearch/' + stuEnrollId,
    method: 'get'
  })
}

// 新增考生查询
export function addEnrollSearch(data) {
  return request({
    url: '/manage/enrollSearch',
    method: 'post',
    data: data
  })
}

// 修改考生查询
export function updateEnrollSearch(data) {
  return request({
    url: '/manage/enrollSearch',
    method: 'put',
    data: data
  })
}

// 删除考生查询
export function delEnrollSearch(stuEnrollId) {
  return request({
    url: '/manage/enrollSearch/' + stuEnrollId,
    method: 'delete'
  })
}
