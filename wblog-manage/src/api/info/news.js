import request from '@/utils/request'

let prefix = 'business/api/v1'

// 查询热门资讯列表
export function listNews(query) {
  return request({
    url: prefix + '/news/list',
    method: 'post',
    data: query
  })
}

// 查询热门资讯详细
export function getNews(id) {
  return request({
    url: prefix + '/news/' + id,
    method: 'get'
  })
}

// 新增热门资讯
export function addNews(data) {
  return request({
    url: prefix + '/news',
    method: 'post',
    data: data
  })
}

// 修改热门资讯
export function updateNews(data) {
  return request({
    url: prefix + '/news',
    method: 'put',
    data: data
  })
}

// 删除热门资讯
export function delNews(id) {
  return request({
    url: prefix + '/news/' + id,
    method: 'delete'
  })
}

// 导出热门资讯
export function exportNews(query) {
  return request({
    url: prefix + '/news/export',
    method: 'get',
    params: query
  })
}
