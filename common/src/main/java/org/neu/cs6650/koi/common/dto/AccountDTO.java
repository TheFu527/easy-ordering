package org.neu.cs6650.koi.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDTO implements Serializable {

    private Integer id;

    private String userId;

    private BigDecimal amount;

    private String password;

}
