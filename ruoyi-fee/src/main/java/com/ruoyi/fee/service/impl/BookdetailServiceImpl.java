package com.ruoyi.fee.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.fee.domain.Bookdetail;
import com.ruoyi.fee.mapper.BookdetailMapper;
import com.ruoyi.fee.service.IBookdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 支出统计Service业务层处理
 *
 * @author admin
 * @date 2020-04-22
 */
@Transactional(readOnly = true)
@Service
public class BookdetailServiceImpl implements IBookdetailService
{
    @Autowired
    private BookdetailMapper bookdetailMapper;

    /**
     * 查询支出统计
     *
     * @param id 支出统计ID
     * @return 支出统计
     */
    @Override
    public Bookdetail selectBookdetailById(Long id)
    {
        return bookdetailMapper.selectBookdetailById(id);
    }

    /**
     * 查询支出统计列表
     *
     * @param bookdetail 支出统计
     * @return 支出统计
     */
    @Override
    public List<Bookdetail> selectBookdetailList(Bookdetail bookdetail)
    {
        return bookdetailMapper.selectBookdetailList(bookdetail);
    }

    /**
     * 新增支出统计
     *
     * @param bookdetail 支出统计
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int insertBookdetail(Bookdetail bookdetail)
    {
        return bookdetailMapper.insertBookdetail(bookdetail);
    }

    /**
     * 修改支出统计
     *
     * @param bookdetail 支出统计
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateBookdetail(Bookdetail bookdetail)
    {
        return bookdetailMapper.updateBookdetail(bookdetail);
    }

    /**
     * 删除支出统计对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteBookdetailByIds(Long[] ids)
    {
        return bookdetailMapper.deleteBookdetailByIds(ids);
    }

    /**
     * 删除支出统计信息
     *
     * @param id 支出统计ID
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteBookdetailById(Long id)
    {
        return bookdetailMapper.deleteBookdetailById(id);
    }
}
