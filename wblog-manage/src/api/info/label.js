import request from '@/utils/request'

let prefix = 'business/api/v1/label'

// 查询标签列表
export function listLabel(query) {
  return request({
    url: prefix + '/find/page/count',
    method: 'post',
    data: query
  })
}

// 查询标签详细
export function getLabel(id) {
  return request({
    url: prefix + '/find/id/' + id,
    method: 'get'
  })
}

// 新增标签
export function addLabel(data) {
  return request({
    url: prefix + '/',
    method: 'post',
    data: data
  })
}

// 修改标签
export function updateLabel(data) {
  return request({
    url:prefix +  '/',
    method: 'put',
    data: data
  })
}

// 删除标签
export function delLabel(id) {
  return request({
    url: prefix + '/' + id,
    method: 'delete'
  })
}

// 导出标签
export function exportLabel(query) {
  return request({
    url: prefix + '/export',
    method: 'get',
    params: query
  })
}
