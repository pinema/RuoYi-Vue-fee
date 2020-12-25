package com.ruoyi.fee.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 结算清单对象 book_summary
 *
 * @author admin
 * @date 2020-04-24
 */
public class BookSummary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 人员 */
    @Excel(name = "人员")
    @ApiModelProperty("人员")
    private String consumer;

    /** 支出总金额 */
    @Excel(name = "支出总金额")
    @ApiModelProperty("支出总金额")
    private Double sum;

    /** 平均支出金额 */
    @Excel(name = "平均支出金额")
    @ApiModelProperty("平均支出金额")
    private Double average;

    /** 结算金额 */
    @Excel(name = "结算金额")
    @ApiModelProperty("结算金额")
    private Double account;

    /** 支出时间 */
    @Excel(name = "支出时间")
    @ApiModelProperty("支出时间")
    private String consumeDate;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setConsumer(String consumer)
    {
        this.consumer = consumer;
    }

    public String getConsumer()
    {
        return consumer;
    }
    public void setSum(Double sum)
    {
        this.sum = sum;
    }

    public Double getSum()
    {
        return sum;
    }
    public void setAverage(Double average)
    {
        this.average = average;
    }

    public Double getAverage()
    {
        return average;
    }
    public void setAccount(Double account)
    {
        this.account = account;
    }

    public Double getAccount()
    {
        return account;
    }
    public void setConsumeDate(String consumeDate)
    {
        this.consumeDate = consumeDate;
    }

    public String getConsumeDate()
    {
        return consumeDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("consumer", getConsumer())
            .append("sum", getSum())
            .append("average", getAverage())
            .append("account", getAccount())
            .append("consumeDate", getConsumeDate())
            .toString();
    }
}
