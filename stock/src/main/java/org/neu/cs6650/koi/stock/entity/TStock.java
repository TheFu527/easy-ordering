package org.neu.cs6650.koi.stock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

public class TStock extends Model<TStock> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String commodityCode;
    private String name;
    private Integer count;

    private Double price;

    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TStock{" +
                "id=" + id +
                ", commodityCode='" + commodityCode + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
