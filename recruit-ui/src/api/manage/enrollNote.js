import request from '@/utils/request'

// 查询通知书信息列表
export function listEnrollNote(query) {
  return request({
    url: '/manage/enrollNote/list',
    method: 'get',
    params: query
  })
}

export function queryEnrollNote(query) {
  return request({
    url: '/manage/enrollNote/query',
    method: 'get',
    params: query
  })
}

// 查询通知书信息详细
export function getEnrollNote(stuEnrollId) {
  return request({
    url: '/manage/enrollNote/' + stuEnrollId,
    method: 'get'
  })
}

// 新增通知书信息
export function addEnrollNote(data) {
  return request({
    url: '/manage/enrollNote',
    method: 'post',
    data: data
  })
}

// 修改通知书信息
export function updateEnrollNote(data) {
  return request({
    url: '/manage/enrollNote',
    method: 'put',
    data: data
  })
}

// 删除通知书信息
export function delEnrollNote(stuEnrollId) {
  return request({
    url: '/manage/enrollNote/' + stuEnrollId,
    method: 'delete'
  })
}
