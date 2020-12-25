package com.ruoyi.fee.service.app;

import com.ruoyi.fee.domain.BookDetailInput;
import com.ruoyi.fee.domain.BookSum;
import com.ruoyi.fee.domain.Bookdetail;
import com.ruoyi.fee.domain.ServerLayResult;

/**
 * @author admin
 * @Title: IAppBookDetailService
 * @ProjectName template
 * @Description: //TODO 支出统计接口层
 * @Company: China united network communication co. LTD
 * @date 2020-3-25 14:42
 */
public interface IAppBookDetailService {

    /**
     * @Author admin
     * @Description //TODO 查询支出费用信息
     * @param bookDetailInput 支出统计
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Date  2020-4-22 16:00
     * @version V1.0.0
     */
    public ServerLayResult consumeInfo(BookDetailInput bookDetailInput);

    /**
     * @Author admin
     * @Description //TODO 新增支出费用信息
     * @param bookList 支出统计
     * @param depId 部门ID
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Date  2020-4-22 16:00
     * @version V1.0.0
     */
    public ServerLayResult addFee(String bookList, Long depId);

    /**
     * @Author admin
     * @Description //TODO 修改支出费用信息
     * @param bookdetail 支出统计
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Date  2020-4-22 16:00
     * @version V1.0.0
     */
    public ServerLayResult editFee(Bookdetail bookdetail);

    /**
     * @Author admin
     * @Description //TODO 删除支出费用信息
     * @param bookdetail 支出统计
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Date  2020-4-22 16:00
     * @version V1.0.0
     */
    public ServerLayResult deleteFee(Bookdetail bookdetail);

    /**
     * @Author admin
     * @Description //TODO 获取人员信息
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Date  2020-4-22 16:00
     * @version V1.0.0
     */
    public ServerLayResult getUser();

    /**
     * @Author admin
     * @Description //TODO 获取汇总费用支出
     * @param bookSum 支出汇总统计
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Date  2020-4-22 16:00
     * @version V1.0.0
     */
    public ServerLayResult getSum(BookSum bookSum);

    /**
     * @Author admin
     * @Description //TODO 获取种类信息
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Date  2020-4-23 16:00
     * @version V1.0.0
     */
    public ServerLayResult getType();

}
