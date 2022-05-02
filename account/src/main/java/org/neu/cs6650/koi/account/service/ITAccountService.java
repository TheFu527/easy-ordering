package org.neu.cs6650.koi.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.neu.cs6650.koi.account.entity.TAccount;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;

public interface ITAccountService extends IService<TAccount> {

    ObjectResponse decreaseAccount(AccountDTO accountDTO);
    ObjectResponse getAccount(String name, String password);
    ObjectResponse createAccount(AccountDTO accountDTO);
}
