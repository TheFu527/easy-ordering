package org.neu.cs6650.koi.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.neu.cs6650.koi.account.entity.TAccount;
import org.apache.ibatis.annotations.Param;
import org.neu.cs6650.koi.common.dto.AccountDTO;

public interface TAccountMapper extends BaseMapper<TAccount> {

    int decreaseAccount(@Param("userId") String userId, @Param("amount") Double amount);
    TAccount getAccount(@Param("userId") String userId, @Param("password") String password);
    int createAccount(@Param("account") TAccount account);
}
