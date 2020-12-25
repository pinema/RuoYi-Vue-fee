package com.ruoyi.fee.service.app.impl;

import cn.hutool.json.JSONArray;
import com.ruoyi.fee.domain.*;
import com.ruoyi.fee.enums.HttpResultCodeEnum;
import com.ruoyi.fee.mapper.BookdetailMapper;
import com.ruoyi.fee.service.app.IAppBookDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional(readOnly = false)
@Slf4j
@Service
public class AppBookDetailServiceImpl implements IAppBookDetailService {

    @Autowired
    private BookdetailMapper bookdetailMapper;

    /**
     * @param bookDetailInput   费用支出
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Author admin
     * @Description //TODO 费用支出统计
     * @Date 2020-4-22 16:00
     * @version V1.0.0
     */
    @Override
    public ServerLayResult consumeInfo(BookDetailInput bookDetailInput) {

        Map<String, Object> resultMap = new HashMap<>();
//        //商家信息
//        RsvMerc rsvMerc = rsvMercMapper.selectRsvMercById(xtjxWechatUser.getDeptId());
//        resultMap.put("rsvMerc", rsvMerc);
//        //将规则转换成数组
//        String deptRules = rsvMerc.getDeptRules();
//        ArrayList listDeptRule = Convert.strToList(deptRules);
//        resultMap.put("listDeptRule", listDeptRule);

        if (StringUtils.isEmpty(bookDetailInput.getDate())) {
            Date date = new Date();
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            String dateStr = sdf.format(date);
            dateStr = dateStr.substring(0, 7);
            bookDetailInput.setDate(dateStr);
        }
        List<BookDetailOutput> bookDetailList = bookdetailMapper.selectBookDetailListApp(bookDetailInput);
        resultMap.put("bookDetailList", bookDetailList);

        return new ServerLayResult(HttpResultCodeEnum.SUCCESS.getCode(), HttpResultCodeEnum.SUCCESS.getMessage(), 0, resultMap);
    }

    /**
     * @param bookList   费用支出
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Author admin
     * @Description //TODO 新增费用支出
     * @Date 2020-4-22 16:00
     * @version V1.0.0
     */
    @Override
    public ServerLayResult addFee(String bookList, Long depId) {
        //插入成功的返回记录数
        int count = 0;
        try {
            List<Bookdetail> bookDetailList = new ArrayList<Bookdetail>();
            // Json转化为list对象
//            bookDetailList = JSONUtil.toCollection(ArrayList.class, Bookdetail.class, bookList);
            setJSONObject(bookList, depId, bookDetailList);

            for (Bookdetail bookDetail : bookDetailList) {

                bookdetailMapper.insertBookdetail(bookDetail);
                count ++;
            }
        return new ServerLayResult(HttpResultCodeEnum.SUCCESS.getCode(), HttpResultCodeEnum.SUCCESS.getMessage(), count, bookList);
    } catch (Exception e) {
        log.error("数据插入异常：{}", e);
        return new ServerLayResult<>(HttpResultCodeEnum.FAIL.getCode(), e.getMessage(), count, bookList);
    }
}

    /**
     * @param jsonStr   JSON串
     * @param bookDetailList   费用支出明细
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Author admin
     * @Description //TODO 获取费用支出明细
     * @Date 2020-4-24 16:00
     * @version V1.0.0
     */
    private void setJSONObject(String jsonStr, Long depId, List<Bookdetail> bookDetailList) throws ParseException {
        JSONArray jsonArray = new JSONArray(jsonStr);
        for (int i = 0; i < jsonArray.size(); i ++) {
            Bookdetail bookDetail = new Bookdetail();

            bookDetail.setDeptId(depId);
            bookDetail.setType(jsonArray.getJSONObject(i).get("type").toString());
            bookDetail.setFee((String) jsonArray.getJSONObject(i).get("fee"));

            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            String dateStr = jsonArray.getJSONObject(i).get("date") + " 00:00:00";
            Date date = sdf.parse(dateStr);

            bookDetail.setDate(date);
            bookDetail.setName(jsonArray.getJSONObject(i).get("name").toString());

            bookDetailList.add(bookDetail);
        }
    }

    /**
     * @param bookdetail   费用支出
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Author admin
     * @Description //TODO 编辑费用支出
     * @Date 2020-4-22 16:00
     * @version V1.0.0
     */
    @Override
    public ServerLayResult editFee(Bookdetail bookdetail) {

        //插入成功的返回记录数
        int count = 0;
        try {
            count = bookdetailMapper.updateBookdetail(bookdetail);
            return new ServerLayResult(HttpResultCodeEnum.SUCCESS.getCode(), HttpResultCodeEnum.SUCCESS.getMessage(), count, bookdetail);
        } catch (Exception e) {
            log.error("数据插入异常：{}", e);
            return new ServerLayResult<>(HttpResultCodeEnum.FAIL.getCode(), e.getMessage(), count, bookdetail);
        }
    }

    /**
     * @param bookdetail   费用支出
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Author admin
     * @Description //TODO 删除费用支出
     * @Date 2020-4-22 16:00
     * @version V1.0.0
     */
    @Override
    public ServerLayResult deleteFee(Bookdetail bookdetail) {
        //插入成功的返回记录数
        int count = 0;
        try {
            count = bookdetailMapper.deleteBookdetailById(bookdetail.getId());
            return new ServerLayResult(HttpResultCodeEnum.SUCCESS.getCode(), HttpResultCodeEnum.SUCCESS.getMessage(), count, bookdetail);
        } catch (Exception e) {
            log.error("数据插入异常：{}", e);
            return new ServerLayResult<>(HttpResultCodeEnum.FAIL.getCode(), e.getMessage(), count, bookdetail);
        }
    }

    /**
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Author admin
     * @Description //TODO 查询人员信息
     * @Date 2020-4-22 16:00
     * @version V1.0.0
     */
    @Override
    public ServerLayResult getUser() {
        Map<String, Object> resultMap = new HashMap<>();
        List<BookUser> bookUserList = bookdetailMapper.selectBookDetailUserList();
        resultMap.put("bookUserList", bookUserList);

        return new ServerLayResult(HttpResultCodeEnum.SUCCESS.getCode(), HttpResultCodeEnum.SUCCESS.getMessage(), 0, resultMap);

    }

    /**
     * @param bookSum   汇总费用支出
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Author admin
     * @Description //TODO 获取汇总费用支出
     * @Date 2020-4-22 16:00
     * @version V1.0.0
     */
    @Override
    public ServerLayResult getSum(BookSum bookSum) {
        Map<String, Object> resultMap = new HashMap<>();
        // 月度汇总list
        List<BookDateSum> bookDateSumList = new ArrayList<BookDateSum>();
        // 月度汇总VO
        BookDateSum bookDateSum = new BookDateSum();
        // 月度汇总用 结算明细list
        List<BookSum> bookDateSumVO = new ArrayList<BookSum>();
        // 结算明细list
        List<BookSum> bookSumList = bookdetailMapper.selectBookSumList(bookSum);

        if (CollectionUtils.isNotEmpty(bookSumList)) {
            for (int i = 0; i < bookSumList.size(); i ++) {
                // 结算金额
                bookSumList.get(i).setAccount(
                        Double.parseDouble(
                                new BigDecimal(bookSumList.get(i).getSum().toString()).subtract(
                                        new BigDecimal(bookSumList.get(i).getAverage().toString())).toString()));

                // 统计月度不同的时候
                if (CollectionUtils.isNotEmpty(bookDateSumVO) &&
                        !StringUtils.equals(bookDateSumVO.get(0).getConsumeDate(),
                        bookSumList.get(i).getConsumeDate())) {
                    // 月度汇总VO
                    bookDateSum.setBookSumList(bookDateSumVO);
                    bookDateSum.setBookDate(bookDateSumVO.get(0).getConsumeDate());
                    // 月度汇总list
                    bookDateSumList.add(bookDateSum);

                    // 初始化月度汇总VO
                    bookDateSum = new BookDateSum();
                    // 初始化结算明细
                    bookDateSumVO = new ArrayList<BookSum>();
                }
                bookDateSumVO.add(bookSumList.get(i));
            }
            if (CollectionUtils.isNotEmpty(bookDateSumVO)) {
                // 月度汇总VO
                bookDateSum.setBookSumList(bookDateSumVO);
                bookDateSum.setBookDate(bookDateSumVO.get(0).getConsumeDate());
                // 月度汇总list
                bookDateSumList.add(bookDateSum);
            }
        }

        resultMap.put("bookDateSumList", bookDateSumList);

        return new ServerLayResult(HttpResultCodeEnum.SUCCESS.getCode(), HttpResultCodeEnum.SUCCESS.getMessage(), 0, resultMap);

    }

    /**
     * @return com.xtjc.common.utils.ServerLayResult
     * @throws
     * @Author admin
     * @Description //TODO 查询支出类别
     * @Date 2020-4-23 16:00
     * @version V1.0.0
     */
    @Override
    public ServerLayResult getType() {
        Map<String, Object> resultMap = new HashMap<>();
        List<BookType> bookTypeList = bookdetailMapper.selectBookTypeList();
        resultMap.put("bookTypeList", bookTypeList);

        return new ServerLayResult(HttpResultCodeEnum.SUCCESS.getCode(), HttpResultCodeEnum.SUCCESS.getMessage(), 0, resultMap);
    }

}
