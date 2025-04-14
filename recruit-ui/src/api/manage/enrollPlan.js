import request from '@/utils/request'

// 查询招生计划列表
export function listEnrollPlan(query) {
  return request({
    url: '/manage/enrollPlan/list',
    method: 'get',
    params: query
  })
}

// 查询招生计划详细
export function getEnrollPlan(planId) {
  return request({
    url: '/manage/enrollPlan/' + planId,
    method: 'get'
  })
}

// 新增招生计划
export function addEnrollPlan(data) {
  return request({
    url: '/manage/enrollPlan',
    method: 'post',
    data: data
  })
}

// 修改招生计划
export function updateEnrollPlan(data) {
  return request({
    url: '/manage/enrollPlan',
    method: 'put',
    data: data
  })
}

// 删除招生计划
export function delEnrollPlan(planId) {
  return request({
    url: '/manage/enrollPlan/' + planId,
    method: 'delete'
  })
}
