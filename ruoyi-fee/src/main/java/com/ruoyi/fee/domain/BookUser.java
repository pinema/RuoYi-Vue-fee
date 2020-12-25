package com.ruoyi.fee.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 支出统计对象 bookdetail
 *
 * @author admin
 * @date 2020-04-22
 */
public class BookUser
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 支出人姓名 */
    @Excel(name = "支出人姓名")
    @ApiModelProperty("支出人姓名")
    private String name;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

}
