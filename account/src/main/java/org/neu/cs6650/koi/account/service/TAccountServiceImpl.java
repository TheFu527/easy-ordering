package org.neu.cs6650.koi.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.neu.cs6650.koi.account.entity.TAccount;
import org.neu.cs6650.koi.account.mapper.TAccountMapper;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.enums.RspStatusEnum;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TAccountServiceImpl extends ServiceImpl<TAccountMapper, TAccount> implements ITAccountService {

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        ObjectResponse<Object> response = new ObjectResponse<>();
        TAccount account = baseMapper.getAccount(accountDTO.getUserId());
        if (account.getAmount() < accountDTO.getAmount().doubleValue()) {
            response.setStatus(RspStatusEnum.MONEY_SHORT.getCode());
            response.setMessage(RspStatusEnum.MONEY_SHORT.getMessage());
            return response;
        }
        baseMapper.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }

    @Override
    public ObjectResponse increaseAccount(AccountDTO accountDTO) {
        ObjectResponse<Object> response = new ObjectResponse<>();

        int account = baseMapper.increaseAccount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());
        if (account > 0) {
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return response;
        }
        response.setStatus(RspStatusEnum.FAIL.getCode());
        response.setMessage(RspStatusEnum.FAIL.getMessage());
        return response;
    }

    @Override
    public ObjectResponse handleLogin(AccountDTO accountDTO) {
        ObjectResponse response = new ObjectResponse<>();
        TAccount tAccount = baseMapper.getAccount(accountDTO.getUserId());
        if (tAccount == null) {
            response.setStatus(RspStatusEnum.NON_EXIST.getCode());
            response.setMessage(RspStatusEnum.NON_EXIST.getMessage());
            return response;
        } else if (!tAccount.getPassword().equals(accountDTO.getPassword())) {
            response.setStatus(RspStatusEnum.NOT_MATCH.getCode());
            response.setMessage(RspStatusEnum.NOT_MATCH.getMessage());
            return response;
        }
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        response.setData(tAccount);
        return response;
    }

    @Override
    public ObjectResponse getAccount(AccountDTO accountDTO) {
        ObjectResponse response = new ObjectResponse<>();
        TAccount tAccount = baseMapper.getAccount(accountDTO.getUserId());

        if (tAccount != null) {
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            response.setData(tAccount);
        } else {
            response.setStatus(RspStatusEnum.NON_EXIST.getCode());
            response.setMessage(RspStatusEnum.NON_EXIST.getMessage());
        }
        return response;
    }

    @Override
    public ObjectResponse createAccount(AccountDTO accountDTO) {
        ObjectResponse<Object> response = new ObjectResponse<>();
        TAccount tAccount = baseMapper.getAccount(accountDTO.getUserId());
        if (tAccount != null) {
            response.setStatus(RspStatusEnum.EXISTED_USER.getCode());
            response.setMessage(RspStatusEnum.NON_EXIST.getMessage());
            return response;
        }

        tAccount = new TAccount();
        BeanUtils.copyProperties(accountDTO, tAccount);
        tAccount.setUserId(accountDTO.getUserId());
        tAccount.setPassword(accountDTO.getPassword());
        tAccount.setAmount(4000.00);

        try {
            baseMapper.createAccount(tAccount);
        } catch (Exception e) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        response.setData(tAccount);
        return response;
    }

}
