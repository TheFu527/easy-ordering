package org.neu.cs6650.koi.business.service;

import com.alibaba.dubbo.config.annotation.Reference;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.dubbo.AccountDubboService;
import org.neu.cs6650.koi.common.dubbo.OrderDubboService;
import org.neu.cs6650.koi.common.dubbo.StockDubboService;
import org.neu.cs6650.koi.common.enums.RspStatusEnum;
import org.neu.cs6650.koi.common.exception.DefaultException;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Reference(version = "1.0.0")
    private StockDubboService stockDubboService;

    @Reference(version = "1.0.0")
    private OrderDubboService orderDubboService;

    @Reference(version = "1.0.0")
    private AccountDubboService accountDubboService;

    private boolean flag;

    @Override
    public ObjectResponse handleBusiness(BusinessDTO businessDTO) {
        log.info("Start global transaction, XID: {}", RootContext.getXID());
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();

        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(businessDTO.getCommodityCode());
        commodityDTO.setCount(businessDTO.getCount());
        ObjectResponse stockResponse = stockDubboService.decreaseStock(commodityDTO);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        orderDTO.setOrderAmount(businessDTO.getAmount());
        ObjectResponse<OrderDTO> response = orderDubboService.createOrder(orderDTO);


        if (stockResponse.getStatus() != 200 || response.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setData(response.getData());
        return objectResponse;
    }

    @Override
    public ObjectResponse getAllStocks() {
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        ObjectResponse stockResponse = stockDubboService.getAllStocks();

        if (stockResponse.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }
        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setData(stockResponse.getData());
        return objectResponse;
    }

    @Override
    public ObjectResponse getStock(CommodityDTO commodityDTO) {
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        ObjectResponse stockResponse = stockDubboService.getStock(commodityDTO);

        if (stockResponse.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        log.info("Get Stock response :" + objectResponse.getData());
        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setData(stockResponse.getData());
        return objectResponse;
    }

    @Override
    public ObjectResponse getOrderByOId(OrderDTO orderDTO) {
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        ObjectResponse orderResponse = orderDubboService.getOrderByOId(orderDTO);

        if (orderResponse.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setData(orderResponse.getData());
        return objectResponse;
    }

    @Override
    public ObjectResponse getOrderByUId(AccountDTO accountDTO) {
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        ObjectResponse orderResponse = orderDubboService.getOrderByUId(accountDTO);

        if (orderResponse.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setData(orderResponse.getData());
        return objectResponse;
    }

    @Override
    //@GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata")
    public ObjectResponse handleRegister(String name, String password) {
        AccountDTO accountDTO = new AccountDTO();
        //create accountDTO object.
        accountDTO.setUserId(name);
        accountDTO.setPassword(password);
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        ObjectResponse accountResponse = accountDubboService.createAccount(accountDTO);
        if (accountResponse.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setData(accountResponse.getData());
        return objectResponse;

    }


    @Override
    //@GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata")
    public ObjectResponse handleLogin(String name, String password) {
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        ObjectResponse loginResponse = handleGetAccount(name, password);
        //if user exist, return user info.
        if (loginResponse.getStatus() == 200) {
            objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
            objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
            objectResponse.setData(loginResponse.getData());
            return objectResponse;
        } else {
            System.out.println("User doesn't exist, add new user.");
            return handleRegister(name, password);
        }
    }

    @Override
    public ObjectResponse handleGetAccount(String name, String password) {
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        ObjectResponse accountResponse = accountDubboService.getAccount(name, password);
        if (accountResponse.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setData(accountResponse.getData());
        return objectResponse;

    }
}
