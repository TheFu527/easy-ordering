package org.neu.cs6650.koi.common.dubbo;

import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;

public interface AccountDubboService {

    ObjectResponse decreaseAccount(AccountDTO accountDTO);

    ObjectResponse increaseAccount(AccountDTO accountDTO);

}
