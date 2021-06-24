import request from '@/utils/request'

let prefix = '/uaa/jobLog'

// 查询调度日志列表
export function listJobLog(query) {
  return request({
    url: prefix + '/list',
    method: 'get',
    params: query
  })
}

// 删除调度日志
export function delJobLog(jobLogId) {
  return request({
    url: prefix + '/' + jobLogId,
    method: 'delete'
  })
}

// 清空调度日志
export function cleanJobLog() {
  return request({
    url: prefix + '/clean',
    method: 'delete'
  })
}

// 导出调度日志
export function exportJobLog(query) {
  return request({
    url: prefix + '/export',
    method: 'get',
    params: query
  })
}
