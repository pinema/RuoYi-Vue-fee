package com.ruoyi.fee.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 支出统计类型 BookType
 *
 * @author admin
 * @date 2020-04-23
 */
public class BookType
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 支出类型 */
    @Excel(name = "支出类型")
    @ApiModelProperty("支出类型")
    private String typeName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }

}
