import request from '@/utils/request'
import { changeUserStatus } from '@/api/system/user'

const prefix = 'notice/m/notice/template'

// 查询列表
export function list(query) {
  return request({
    url: prefix + '/list',
    method: 'post',
    data: query
  })
}

// 查询详细
export function get(id) {
  return request({
    url: prefix + '/' + id,
    method: 'get'
  })
}

// 新增
export function add(data) {
  return request({
    url: prefix + '',
    method: 'post',
    data: data
  })
}

// 修改
export function update(data) {
  return request({
    url: prefix + '',
    method: 'put',
    data: data
  })
}

// 删除
export function del(id) {
  return request({
    url: prefix + '/' + id,
    method: 'delete'
  })
}

// 修改状态
export function changeStatus(id, status) {
  let data = {
    id: id,
    status: status
  }
  return request({
    url: prefix + '',
    method: 'put',
    data: data
  })
}
