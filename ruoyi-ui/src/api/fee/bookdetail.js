import request from '@/utils/request'

// 查询支出统计列表
export function listBookdetail(query) {
  return request({
    url: '/fee/bookdetail/list',
    method: 'get',
    params: query
  })
}

// 查询支出统计详细
export function getBookdetail(id) {
  return request({
    url: '/fee/bookdetail/' + id,
    method: 'get'
  })
}

// 新增支出统计
export function addBookdetail(data) {
  return request({
    url: '/fee/bookdetail',
    method: 'post',
    data: data
  })
}

// 修改支出统计
export function updateBookdetail(data) {
  return request({
    url: '/fee/bookdetail',
    method: 'put',
    data: data
  })
}

// 删除支出统计
export function delBookdetail(id) {
  return request({
    url: '/fee/bookdetail/' + id,
    method: 'delete'
  })
}

// 导出支出统计
export function exportBookdetail(query) {
  return request({
    url: '/fee/bookdetail/export',
    method: 'get',
    params: query
  })
}