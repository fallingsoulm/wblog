import request from '@/utils/request'

let prefix = 'business/api/v1'

// 查询文章列表
export function listArticle(query) {
  return request({
    url: prefix + '/info/article/list',
    method: 'post',
    data: query
  })
}

// 查询文章详细
export function getArticle(id) {
  return request({
    url: prefix + '/info/article/' + id,
    method: 'get'
  })
}

// 新增文章
export function addArticle(data) {
  return request({
    url: prefix + '/info/article',
    method: 'post',
    data: data
  })
}

// 修改文章
export function updateArticle(data) {
  return request({
    url: prefix + '/info/article',
    method: 'put',
    data: data
  })
}

// 删除文章
export function delArticle(id) {
  return request({
    url: prefix + '/info/article/' + id,
    method: 'delete'
  })
}

// 导出文章
export function exportArticle(query) {
  return request({
    url: prefix + '/info/article/export',
    method: 'get',
    params: query
  })
}

// 修改状态
export function updateStatusArticle(ids,status) {
  return request({
    url: prefix + '/info/article/update/status/'+ids+"/"+status,
    method: 'get',

  })
}

// 重命名
export function rename(id) {
  return request({
    url: prefix + '/info/article/rename/'+id,
    method: 'get',

  })
}






