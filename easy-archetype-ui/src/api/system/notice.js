import request from '@/utils/request'

let prefix = '/uaa/system/notice'

// 查询公告列表
export function listNotice(query) {
  return request({
    url: prefix + '/list',
    method: 'get',
    params: query
  })
}

// 查询公告详细
export function getNotice(noticeId) {
  return request({
    url: prefix + '/' + noticeId,
    method: 'get'
  })
}

// 新增公告
export function addNotice(data) {
  return request({
    url: prefix + '',
    method: 'post',
    data: data
  })
}

// 修改公告
export function updateNotice(data) {
  return request({
    url: prefix + '',
    method: 'put',
    data: data
  })
}

// 删除公告
export function delNotice(noticeId) {
  return request({
    url: prefix + '/' + noticeId,
    method: 'delete'
  })
}
