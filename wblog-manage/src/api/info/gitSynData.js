import request from '@/utils/request'

let prefix = 'business/api/v1/git/syn/data'

// 查询git同步信息配置列表
export function listGitSynData(query) {
  return request({
    url: prefix + '/list',
    method: 'post',
    data: query
  })
}

// 查询git同步信息配置详细
export function getGitSynData(id) {
  return request({
    url: prefix + '/' + id,
    method: 'get'
  })
}

// 新增git同步信息配置
export function addGitSynData(data) {
  return request({
    url: prefix + '/',
    method: 'post',
    data: data
  })
}

// 修改git同步信息配置
export function updateGitSynData(data) {
  return request({
    url: prefix + '/',
    method: 'put',
    data: data
  })
}

// 删除git同步信息配置
export function delGitSynData(id) {
  return request({
    url: prefix + '/' + id,
    method: 'delete'
  })
}

// 导出git同步信息配置
export function exportGitSynData(query) {
  return request({
    url: prefix + '/export',
    method: 'get',
    params: query
  })
}


// 修改状态
export function updateStatus(ids, status) {
  return request({
    url: prefix + '/update/status/' + ids + '/' + status,
    method: 'get'
  })
}

// 文章同步
export function syn(id) {
  return request({
    url: prefix + '/syn/' + id,
    method: 'get'
  })
}
