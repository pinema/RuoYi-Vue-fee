package com.ruoyi.fee.service;

import com.ruoyi.fee.domain.Bookdetail;

import java.util.List;

/**
 * 支出统计Service接口
 *
 * @author admin
 * @date 2020-04-22
 */
public interface IBookdetailService
{
    /**
     * 查询支出统计
     *
     * @param id 支出统计ID
     * @return 支出统计
     */
    public Bookdetail selectBookdetailById(Long id);

    /**
     * 查询支出统计列表
     *
     * @param bookdetail 支出统计
     * @return 支出统计集合
     */
    public List<Bookdetail> selectBookdetailList(Bookdetail bookdetail);

    /**
     * 新增支出统计
     *
     * @param bookdetail 支出统计
     * @return 结果
     */
    public int insertBookdetail(Bookdetail bookdetail);

    /**
     * 修改支出统计
     *
     * @param bookdetail 支出统计
     * @return 结果
     */
    public int updateBookdetail(Bookdetail bookdetail);

    /**
     * 批量删除支出统计
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBookdetailByIds(Long[] ids);

    /**
     * 删除支出统计信息
     *
     * @param id 支出统计ID
     * @return 结果
     */
    public int deleteBookdetailById(Long id);
}
