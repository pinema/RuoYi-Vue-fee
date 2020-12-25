package com.ruoyi.fee.controller.app;

import com.ruoyi.fee.domain.BookDetailInput;
import com.ruoyi.fee.domain.BookSum;
import com.ruoyi.fee.domain.Bookdetail;
import com.ruoyi.fee.domain.ServerLayResult;
import com.ruoyi.fee.service.app.IAppBookDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author admin
 * @date 2020-4-22 16:00
 */
@Api("支出统计")
@RestController
@RequestMapping("/app/bookdetail")
public class AppBookDetailController {

    @Autowired
    private IAppBookDetailService appBookDetailService;

    /**
     * @Author admin
     * @Description 查询支出统计
     * @param bookDetailInput 费用支出统计
     * @return com.xtjc.common.utils.ServerLayResult
     * @Date  2020-4-22 19:32
     */
    @ApiOperation(value = "费用支出统计", notes = "费用支出统计")
    @GetMapping("/consumeInfo")
    public ServerLayResult consumeInfo(@ApiParam(value = "费用支出统计", required = true)@Valid BookDetailInput bookDetailInput){

        return appBookDetailService.consumeInfo(bookDetailInput);
    }

    /**
     * @Author admin
     * @Description 新增支出统计
     * @param bookList 费用支出统计
     * @return com.xtjc.common.utils.ServerLayResult
     * @Date  2020-4-22 19:32
     */
    @ApiOperation(value = "费用支出统计", notes = "费用支出统计")
    @GetMapping("/addFee")
    public ServerLayResult addFee(@ApiParam(value = "费用支出统计", required = true)@RequestParam("bookList") String bookList){

//        return appBookDetailService.addFee(bookList, ShiroUtils.getDeptId());
        return appBookDetailService.addFee(bookList, 100l);
    }

    /**
     * @Author admin
     * @Description 修改费用支出
     * @param bookdetail 费用支出统计
     * @return com.xtjc.common.utils.ServerLayResult
     * @Date  2020-4-22 19:32
     */
    @ApiOperation(value = "费用支出统计", notes = "费用支出统计")
    @GetMapping("/editFee")
    public ServerLayResult editFee(@ApiParam(value = "费用支出统计", required = true)@Valid Bookdetail bookdetail){

        return appBookDetailService.editFee(bookdetail);
    }

    /**
     * @Author admin
     * @Description 删除费用支出
     * @param bookdetail 费用支出统计
     * @return com.xtjc.common.utils.ServerLayResult
     * @Date  2020-4-22 19:32
     */
    @ApiOperation(value = "费用支出统计", notes = "费用支出统计")
    @GetMapping("/delFee")
    public ServerLayResult deleteFee(@ApiParam(value = "费用支出统计", required = true)@Valid Bookdetail bookdetail){

        return appBookDetailService.deleteFee(bookdetail);
    }

    /**
     * @Author admin
     * @Description 获取人员信息
     * @return com.xtjc.common.utils.ServerLayResult
     * @Date  2020-4-22 19:32
     */
    @ApiOperation(value = "获取人员信息", notes = "获取人员信息")
    @GetMapping("/userInfo")
    public ServerLayResult userInfo(){

        return appBookDetailService.getUser();
    }

    /**
     * @Author admin
     * @Description 获取汇总信息
     * @return com.xtjc.common.utils.ServerLayResult
     * @Date  2020-4-22 19:32
     */
    @ApiOperation(value = "获取汇总信息", notes = "获取汇总信息")
    @GetMapping("/sumInfo")
    public ServerLayResult sumInfo(@ApiParam(value = "汇总费用支出", required = true)@Valid BookSum bookSum){

        return appBookDetailService.getSum(bookSum);
    }

    /**
     * @Author admin
     * @Description 获取类别信息
     * @return com.xtjc.common.utils.ServerLayResult
     * @Date  2020-4-23 19:32
     */
    @ApiOperation(value = "获取类别信息", notes = "获取类别信息")
    @GetMapping("/typeInfo")
    public ServerLayResult typeInfo(){

        return appBookDetailService.getType();
    }

}
