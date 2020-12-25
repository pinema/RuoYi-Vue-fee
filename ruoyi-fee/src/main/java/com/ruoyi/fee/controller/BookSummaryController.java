package com.ruoyi.fee.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fee.domain.BookSummary;
import com.ruoyi.fee.service.IBookSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 结算清单Controller
 *
 * @author admin
 * @date 2020-04-24
 */
@RestController
@RequestMapping("/fee/summary")
public class BookSummaryController extends BaseController
{

    @Autowired
    private IBookSummaryService bookSummaryService;

    /**
     * 查询结算清单列表
     */
    @PreAuthorize("@ss.hasPermi('fee:summary:list')")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookSummary bookSummary)
    {
        startPage();
        List<BookSummary> list = bookSummaryService.selectBookSummaryList(bookSummary);
        return getDataTable(list);
    }

    /**
     * 导出结算清单列表
     */
    @PreAuthorize("@ss.hasPermi('fee:summary:export')")
    @Log(title = "结算清单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ResponseBody
    public AjaxResult export(BookSummary bookSummary)
    {
        List<BookSummary> list = bookSummaryService.selectBookSummaryList(bookSummary);
        ExcelUtil<BookSummary> util = new ExcelUtil<>(BookSummary.class);
        return util.exportExcel(list, "summary");
    }

    /**
     * 新增保存结算清单
     */
    @PreAuthorize("@ss.hasPermi('fee:summary:add')")
    @Log(title = "结算清单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookSummary bookSummary)
    {
        return toAjax(bookSummaryService.insertBookSummary(bookSummary));
    }

    /**
     * 修改保存结算清单
     */
    @PreAuthorize("@ss.hasPermi('fee:summary:edit')")
    @Log(title = "结算清单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookSummary bookSummary)
    {
        return toAjax(bookSummaryService.updateBookSummary(bookSummary));
    }

    /**
     * 删除结算清单
     */
    @PreAuthorize("@ss.hasPermi('fee:summary:remove')")
    @Log(title = "结算清单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookSummaryService.deleteBookSummaryByIds(ids));
    }
}
