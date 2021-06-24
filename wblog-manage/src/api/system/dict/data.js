import request from '@/utils/request'

let prefix = '/uaa/system/dict/data'

// 查询字典数据列表
export function listData(query) {
  return request({
    url: prefix + '/list',
    method: 'post',
    data: query
  })
}

// 查询字典数据详细
export function getData(dictCode) {
  return request({
    url: prefix + '/' + dictCode,
    method: 'get'
  })
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request({
    url: prefix + '/type/' + dictType,
    method: 'get'
  })
}

// 新增字典数据
export function addData(data) {
  return request({
    url: prefix + '',
    method: 'post',
    data: data
  })
}

// 修改字典数据
export function updateData(data) {
  return request({
    url: prefix + '',
    method: 'put',
    data: data
  })
}

// 删除字典数据
export function delData(dictCode) {
  return request({
    url: prefix + '/' + dictCode,
    method: 'delete'
  })
}

// 导出字典数据
export function exportData(query) {
  return request({
    url: prefix + '/export',
    method: 'get',
    params: query
  })
}
