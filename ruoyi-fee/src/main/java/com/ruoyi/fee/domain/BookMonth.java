package com.ruoyi.fee.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 月度 BookMonth
 *
 * @author admin
 * @date 2020-04-24
 */
public class BookMonth
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 支出月度 */
    @Excel(name = "支出月度")
    @ApiModelProperty("支出月度")
    private String month;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
