package com.ruoyi.fee.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 结算清单对象
 *
 * @author admin
 * @date 2020-04-24
 */
public class BookDateSum
{

    /** 日期 */
    String bookDate;

    /** 结算清单 */
    List<BookSum> bookSumList = new ArrayList<BookSum>();

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public List<BookSum> getBookSumList() {
        return bookSumList;
    }

    public void setBookSumList(List<BookSum> bookSumList) {
        this.bookSumList = bookSumList;
    }
}
