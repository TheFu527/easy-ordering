package org.neu.cs6650.koi.account.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import io.seata.core.context.RootContext;
import org.neu.cs6650.koi.account.service.ITAccountService;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dubbo.AccountDubboService;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0", protocol = "${dubbo.protocol.id}", application = "${dubbo.application.id}",
    registry = "${dubbo.registry.id}", timeout = 3000)
public class AccountDubboServiceImpl implements AccountDubboService {

    @Autowired
    private ITAccountService accountService;

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        System.out.println("Global transaction id: " + RootContext.getXID());
        return accountService.decreaseAccount(accountDTO);
    }
}
