import request from '@/utils/request'

let prefix = 'business/api/v1/album/'

// 查询文章专辑列表
export function listAlbum(query) {
  return request({
    url: prefix + '/list',
    method: 'post',
    data: query
  })
}

// 查询文章专辑详细
export function getAlbum(id) {
  return request({
    url: prefix + '/' + id,
    method: 'get'
  })
}

// 新增文章专辑
export function addAlbum(data) {
  return request({
    url: prefix + '/',
    method: 'post',
    data: data
  })
}

// 修改文章专辑
export function updateAlbum(data) {
  return request({
    url: prefix + '/',
    method: 'put',
    data: data
  })
}

// 删除文章专辑
export function delAlbum(id) {
  return request({
    url: prefix + '/' + id,
    method: 'delete'
  })
}

// 导出文章专辑
export function exportAlbum(query) {
  return request({
    url: prefix + '/export',
    method: 'get',
    params: query
  })
}

// 查询绑定的文章列表
export function bindArticle(query) {
  return request({
    url: prefix + '/bind/article',
    method: 'post',
    data: query
  })
}

// 删除文章绑定
export function delBindArticle(id) {
  return request({
    url: prefix + '/delete/bind/' + id,
    method: 'delete'
  })
}

// 修改绑定排序
export function updateBindSort(id, sort) {
  return request({
    url: prefix + '/update/bind/sort/' + id + '/' + sort,
    method: 'get'
  })
}


// 查询未绑定的文章列表
export function noBindArticle(query) {
  return request({
    url: prefix + '/no/bind/article',
    method: 'post',
    data: query
  })
}

//  绑定
export function doBind(id, articleIds) {
  return request({
    url: prefix + '/bind/' + id,
    method: 'post',
    data: articleIds
  })
}
