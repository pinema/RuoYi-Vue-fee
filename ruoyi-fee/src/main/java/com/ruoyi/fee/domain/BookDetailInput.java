package com.ruoyi.fee.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 支出统计对象 bookdetail
 *
 * @author admin
 * @date 2020-04-22
 */
public class BookDetailInput extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 企业id */
    @Excel(name = "企业id")
    @ApiModelProperty("企业id")
    private Long deptId;

    /** 支出类型 */
    @Excel(name = "支出类型")
    @ApiModelProperty("支出类型")
    private String type;

    /** 支出金额 */
    @Excel(name = "支出金额")
    @ApiModelProperty("支出金额")
    private String fee;

    /** 支出时间 */
    @Excel(name = "支出时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("支出时间")
    private String date;

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
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setFee(String fee)
    {
        this.fee = fee;
    }

    public String getFee()
    {
        return fee;
    }
    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptId", getDeptId())
            .append("type", getType())
            .append("fee", getFee())
            .append("date", getDate())
            .append("name", getName())
            .toString();
    }
}
