package org.neu.cs6650.koi.account.controller;

import org.neu.cs6650.koi.account.service.ITAccountService;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Slf4j
public class TAccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TAccountController.class);

    @Autowired
    private ITAccountService accountService;

    @PostMapping("/dec_account")
    ObjectResponse decreaseAccount(@RequestBody AccountDTO accountDTO) {
        LOGGER.info("decreaseAccount：{}", accountDTO.toString());
        return accountService.decreaseAccount(accountDTO);
    }

    @GetMapping("/test_global_lock")
    void testGlobalLock() {
        LOGGER.info("testGlobalLock");
        accountService.testGlobalLock();
    }
}

