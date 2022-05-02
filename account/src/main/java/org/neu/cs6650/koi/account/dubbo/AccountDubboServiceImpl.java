package org.neu.cs6650.koi.account.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.neu.cs6650.koi.account.service.ITAccountService;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dubbo.AccountDubboService;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0", protocol = "${dubbo.protocol.id}", application = "${dubbo.application.id}",
    registry = "${dubbo.registry.id}", timeout = 3000)
@Slf4j
public class AccountDubboServiceImpl implements AccountDubboService {

    @Autowired
    private ITAccountService accountService;

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        log.info("Global transaction id: {}", RootContext.getXID());
        log.info("decreaseAccount：{}", accountDTO.toString());
        return accountService.decreaseAccount(accountDTO);
    }

    @Override
    public ObjectResponse getAccount(AccountDTO accountDTO) {
        return accountService.getAccount(accountDTO);
    }

    @Override
    public ObjectResponse createAccount(AccountDTO accountDTO) {
        log.info("Global transaction id: {}", RootContext.getXID());
        log.info("createOrder: {}", accountDTO.toString());
        return accountService.createAccount(accountDTO);
    }

    @Override
    public ObjectResponse increaseAccount(AccountDTO accountDTO) {
        log.info("Global transaction id: {}", RootContext.getXID());
        log.info("increaseAccount：{}", accountDTO.toString());
        return accountService.increaseAccount(accountDTO);
    }

    @Override
    public ObjectResponse handleLogin(AccountDTO accountDTO) {
        return accountService.handleLogin(accountDTO);
    }
}
