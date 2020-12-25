import request from '@/utils/request'

// 查询结算清单列表
export function listSummary(query) {
  return request({
    url: '/fee/summary/list',
    method: 'get',
    params: query
  })
}

// 查询结算清单详细
export function getSummary(id) {
  return request({
    url: '/fee/summary/' + id,
    method: 'get'
  })
}

// 新增结算清单
export function addSummary(data) {
  return request({
    url: '/fee/summary',
    method: 'post',
    data: data
  })
}

// 修改结算清单
export function updateSummary(data) {
  return request({
    url: '/fee/summary',
    method: 'put',
    data: data
  })
}

// 删除结算清单
export function delSummary(id) {
  return request({
    url: '/fee/summary/' + id,
    method: 'delete'
  })
}

// 导出结算清单
export function exportSummary(query) {
  return request({
    url: '/fee/summary/export',
    method: 'get',
    params: query
  })
}