package com.ruoyi.fee.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.fee.domain.BookMonth;
import com.ruoyi.fee.domain.BookSummary;
import com.ruoyi.fee.mapper.BookSummaryMapper;
import com.ruoyi.fee.service.IBookSummaryService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 结算清单Service业务层处理
 *
 * @author admin
 * @date 2020-04-24
 */
@Transactional(readOnly = true)
@Service
public class BookSummaryServiceImpl implements IBookSummaryService
{
    @Autowired
    private BookSummaryMapper bookSummaryMapper;

    /**
     * 查询结算清单
     *
     * @param id 结算清单ID
     * @return 结算清单
     */
    @Override
    public BookSummary selectBookSummaryById(Long id)
    {
        return bookSummaryMapper.selectBookSummaryById(id);
    }

    /**
     * 查询结算清单列表
     *
     * @param bookSummary 结算清单
     * @return 结算清单
     */
    @Override
    public List<BookSummary> selectBookSummaryList(BookSummary bookSummary)
    {
        List<BookSummary> bookSummaryList = bookSummaryMapper.selectBookSummaryList(bookSummary);
        if (CollectionUtils.isNotEmpty(bookSummaryList)) {
            for ( int i = 0; i < bookSummaryList.size(); i ++) {
                bookSummaryList.get(i).setAccount(
                        Double.parseDouble(
                                new BigDecimal(bookSummaryList.get(i).getSum().toString()).subtract(
                                        new BigDecimal(bookSummaryList.get(i).getAverage().toString())
                                ).toString()
                        )
                );

            }
        }
//        return bookSummaryMapper.selectBookSummaryList(bookSummary);
        return bookSummaryList;
    }

    /**
     * 新增结算清单
     *
     * @param bookSummary 结算清单
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int insertBookSummary(BookSummary bookSummary)
    {
        return bookSummaryMapper.insertBookSummary(bookSummary);
    }

    /**
     * 修改结算清单
     *
     * @param bookSummary 结算清单
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateBookSummary(BookSummary bookSummary)
    {
        return bookSummaryMapper.updateBookSummary(bookSummary);
    }

    /**
     * 删除结算清单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteBookSummaryByIds(String ids)
    {
        return bookSummaryMapper.deleteBookSummaryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除结算清单信息
     *
     * @param id 结算清单ID
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteBookSummaryById(Long id)
    {
        return bookSummaryMapper.deleteBookSummaryById(id);
    }


    /**
     * 查询月度列表
     *
     * @return 月度列表
     */
    @Override
    public List<BookMonth> selectBookMonthList()
    {
        List<BookMonth> bookSummaryList = bookSummaryMapper.selectBookMonthList();

        return bookSummaryList;
    }

}
