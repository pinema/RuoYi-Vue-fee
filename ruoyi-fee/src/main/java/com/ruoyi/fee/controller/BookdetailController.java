package com.ruoyi.fee.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fee.domain.Bookdetail;
import com.ruoyi.fee.service.IBookdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 支出统计Controller
 *
 * @author admin
 * @date 2020-04-22
 */
@RestController
@RequestMapping("/fee/bookdetail")
public class BookdetailController extends BaseController
{

    @Autowired
    private IBookdetailService bookdetailService;

    /**
     * 查询支出统计列表
     */
    @PreAuthorize("@ss.hasPermi('fee:bookdetail:list')")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Bookdetail bookdetail)
    {
        startPage();
        bookdetail.setDeptId(SecurityUtils.getDeptId());
        List<Bookdetail> list = bookdetailService.selectBookdetailList(bookdetail);
        return getDataTable(list);
    }

    /**
     * 导出支出统计列表
     */
    @PreAuthorize("@ss.hasPermi('fee:bookdetail:export')")
    @Log(title = "支出统计", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ResponseBody
    public AjaxResult export(Bookdetail bookdetail)
    {
        bookdetail.setDeptId(SecurityUtils.getDeptId());
        List<Bookdetail> list = bookdetailService.selectBookdetailList(bookdetail);
        ExcelUtil<Bookdetail> util = new ExcelUtil<>(Bookdetail.class);
        return util.exportExcel(list, "bookdetail");
    }

    /**
     * 获取支出统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('fee:bookdetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bookdetailService.selectBookdetailById(id));
    }

    /**
     * 新增保存支出统计
     */
    @PreAuthorize("@ss.hasPermi('fee:bookdetail:add')")
    @Log(title = "支出统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addSave(@Validated @RequestBody Bookdetail bookdetail)
    {
        bookdetail.setDeptId(SecurityUtils.getDeptId());
        return toAjax(bookdetailService.insertBookdetail(bookdetail));
    }

    /**
     * 修改保存支出统计
     */
    @PreAuthorize("@ss.hasPermi('fee:bookdetail:edit')")
    @Log(title = "支出统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editSave(@Validated @RequestBody Bookdetail bookdetail)
    {
        return toAjax(bookdetailService.updateBookdetail(bookdetail));
    }

    /**
     * 删除支出统计
     */
    @PreAuthorize("@ss.hasPermi('fee:bookdetail:remove')")
    @Log(title = "支出统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bookdetailService.deleteBookdetailByIds(ids));
    }
}
