import request from '@/utils/request'

export function getStaticsPie(query) {
  return request({
    url: '/manage/statics',
    method: 'get',
    params: query
  })
}
