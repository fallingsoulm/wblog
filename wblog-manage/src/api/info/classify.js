import request from '@/utils/request'

let prefix = 'business/api/v1'

// 查询分类
export function listAll() {
  return request({
    url: prefix + '/classify/list',
    method: 'get'

  })
}
