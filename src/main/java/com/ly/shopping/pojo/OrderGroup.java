package com.ly.shopping.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 订单组类
 * @implNote 该类用于辅助后台图表的生成，亦不属于实体类

 */
public class OrderGroup {
    private Date payDate/*订单组的支付日期*/;

    private Integer productOrder_count/*订单组的统计个数*/;

    private Byte status/*订单组的订单状态*/;

    public String getPayDate() {
        if (payDate != null) {
            SimpleDateFormat time = new SimpleDateFormat("MM/dd", Locale.UK);
            return time.format(payDate);
        }
        return null;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getProductOrder_count() {
        return productOrder_count;
    }

    public void setProductOrder_count(Integer productOrder_count) {
        this.productOrder_count = productOrder_count;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
