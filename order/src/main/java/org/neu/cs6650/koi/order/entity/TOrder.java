package org.neu.cs6650.koi.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

public class TOrder extends Model<TOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String order_no;
    private String user_id;
    private String commodity_code;
    private Integer count;
    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return order_no;
    }

    public void setOrderNo(String orderNo) {
        this.order_no = orderNo;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
    }

    public String getCommodityCode() {
        return commodity_code;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodity_code = commodityCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TOrder{" + ", id=" + id + ", orderNo=" + order_no + ", userId=" + user_id + ", commodityCode="
            + commodity_code + ", count=" + count + ", amount=" + amount + "}";
    }
}
