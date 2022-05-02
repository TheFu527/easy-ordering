package org.neu.cs6650.koi.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CommodityDTO implements Serializable {

    private Integer id;

    private String commodityCode;

    private String name;

    private Integer count;

    private BigDecimal price;

    private String image;
}
