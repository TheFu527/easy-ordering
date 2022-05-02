package org.neu.cs6650.koi.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.neu.cs6650.koi.account.entity.TAccount;
import org.neu.cs6650.koi.account.mapper.TAccountMapper;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.enums.RspStatusEnum;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import io.seata.spring.annotation.GlobalLock;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TAccountServiceImpl extends ServiceImpl<TAccountMapper, TAccount> implements ITAccountService {

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        int account = baseMapper.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());
        ObjectResponse<Object> response = new ObjectResponse<>();
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
    public ObjectResponse getAccount(String user_name, String password) {
        ObjectResponse<AccountDTO> response = new ObjectResponse<>();
        TAccount tAccount = baseMapper.getAccount(user_name, password);
        if(tAccount != null){
            AccountDTO accountDTO= new AccountDTO();
            accountDTO.setId(tAccount.getId());
            accountDTO.setUserId(tAccount.getUserId());
            accountDTO.setAmount(BigDecimal.valueOf(tAccount.getAmount()));
            accountDTO.setPassword(tAccount.getPassword());

            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            response.setData(accountDTO);
            return response;
        }

            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;

    }

    @Override
    public ObjectResponse createAccount(AccountDTO accountDTO) {
        ObjectResponse<AccountDTO> response = new ObjectResponse<>();

        TAccount tAccount = new TAccount();
        BeanUtils.copyProperties(accountDTO, tAccount);
        tAccount.setUserId(accountDTO.getUserId());
        tAccount.setPassword(accountDTO.getPassword());
        try {
            //get the new id of the new user.
            int id = baseMapper.createAccount(tAccount);
            accountDTO.setId(id);
        } catch (Exception e) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        response.setData(accountDTO);
        return response;
    }

}
