package org.neu.cs6650.koi.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.neu.cs6650.koi.account.entity.TAccount;
import org.neu.cs6650.koi.account.mapper.TAccountMapper;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.enums.RspStatusEnum;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.springframework.stereotype.Service;

@Service
public class TAccountServiceImpl extends ServiceImpl<TAccountMapper, TAccount> implements ITAccountService {

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        ObjectResponse<Object> response = new ObjectResponse<>();
//        TAccount account = baseMapper.getAccount(accountDTO.getUserId());
//        if (account.getAmount() < accountDTO.getAmount().doubleValue()) {
//            response.setStatus(RspStatusEnum.MONEY_SHORT.getCode());
//            response.setMessage(RspStatusEnum.MONEY_SHORT.getMessage());
//            return response;
//        }
        baseMapper.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }
}
