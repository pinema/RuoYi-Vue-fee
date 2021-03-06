package com.ruoyi.fee.service;

import com.ruoyi.fee.domain.BookMonth;
import com.ruoyi.fee.domain.BookSummary;

import java.util.List;

/**
 * 结算清单Service接口
 *
 * @author admin
 * @date 2020-04-24
 */
public interface IBookSummaryService
{
    /**
     * 查询结算清单
     *
     * @param id 结算清单ID
     * @return 结算清单
     */
    public BookSummary selectBookSummaryById(Long id);

    /**
     * 查询结算清单列表
     *
     * @param bookSummary 结算清单
     * @return 结算清单集合
     */
    public List<BookSummary> selectBookSummaryList(BookSummary bookSummary);

    /**
     * 新增结算清单
     *
     * @param bookSummary 结算清单
     * @return 结果
     */
    public int insertBookSummary(BookSummary bookSummary);

    /**
     * 修改结算清单
     *
     * @param bookSummary 结算清单
     * @return 结果
     */
    public int updateBookSummary(BookSummary bookSummary);

    /**
     * 批量删除结算清单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBookSummaryByIds(String ids);

    /**
     * 删除结算清单信息
     *
     * @param id 结算清单ID
     * @return 结果
     */
    public int deleteBookSummaryById(Long id);

    /**
     * 查询月度列表
     *
     * @return 月度列表
     */
    public List<BookMonth> selectBookMonthList();

}
