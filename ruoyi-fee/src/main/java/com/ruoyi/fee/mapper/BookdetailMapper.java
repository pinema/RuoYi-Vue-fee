package com.ruoyi.fee.mapper;

import com.ruoyi.fee.domain.*;

import java.util.List;

/**
 * 支出统计Mapper接口
 *
 * @author admin
 * @date 2020-04-22
 */
public interface BookdetailMapper
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
     * 删除支出统计
     *
     * @param id 支出统计ID
     * @return 结果
     */
    public int deleteBookdetailById(Long id);

    /**
     * 批量删除支出统计
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBookdetailByIds(Long[] ids);


    /**
     * 查询支出统计列表 小程序端
     *
     * @param bookDetailInput 支出统计
     * @return 支出统计集合
     */
    public List<BookDetailOutput> selectBookDetailListApp(BookDetailInput bookDetailInput);

    /**
     * 查询人员信息 小程序端
     *
     * @return 支出人员信息集合
     */
    public List<BookUser> selectBookDetailUserList();

    /**
     * 查询支出统计汇总 小程序端
     *
     * @return 支出统计汇总
     */
    public List<BookSum> selectBookSumList(BookSum bookSum);


    /**
     * 查询类别信息 小程序端
     *
     * @return 支出类别信息集合
     */
    public List<BookType> selectBookTypeList();

}
