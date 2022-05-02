package org.neu.cs6650.koi.order.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.dubbo.AccountDubboService;
import org.neu.cs6650.koi.common.dubbo.StockDubboService;
import org.neu.cs6650.koi.common.enums.RspStatusEnum;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.order.entity.TOrder;
import org.neu.cs6650.koi.order.mapper.TOrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

    @Reference(version = "1.0.0")
    private AccountDubboService accountDubboService;
    @Reference(version = "1.0.0")
    private StockDubboService stockDubboService;

    @Override
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        ObjectResponse<OrderDTO> response = new ObjectResponse<>();

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getOrderAmount());
        ObjectResponse objectResponse = accountDubboService.decreaseAccount(accountDTO);

        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));

        TOrder tOrder = new TOrder();
        BeanUtils.copyProperties(orderDTO, tOrder);
        tOrder.setCount(orderDTO.getOrderCount());
        tOrder.setAmount(orderDTO.getOrderAmount().doubleValue());
        String time = getOrderTime();
        String description = String.format("%s: user %s placed an order on commodity %s, the count is %s, total money is %s", time, orderDTO.getUserId(), orderDTO.getCommodityCode(), orderDTO.getOrderCount(), orderDTO.getOrderAmount());
        tOrder.setDescription(description);
        try {
            baseMapper.createOrder(tOrder);
        } catch (Exception e) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        if (objectResponse.getStatus() != 200) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }

    @Override
    public ObjectResponse getOrderByOId(OrderDTO orderDTO) {
        TOrder order = baseMapper.getOrderByOId(orderDTO.getOrderNo());
        ObjectResponse<Object> response = new ObjectResponse<>();
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setData(order);
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }

    @Override
    public ObjectResponse getOrderByUId(AccountDTO accountDTO) {
        List<TOrder> orderList = baseMapper.getOrderByUId(accountDTO.getUserId());
        ObjectResponse<Object> response = new ObjectResponse<>();
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setData(orderList);
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }

    @Override
    public ObjectResponse deleteOrder(OrderDTO orderDTO) {
        ObjectResponse<OrderDTO> response = new ObjectResponse<>();
        TOrder order = baseMapper.getOrderByOId(orderDTO.getOrderNo());
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(order.getCommodityCode());
        commodityDTO.setCount(order.getCount());
        ObjectResponse stockResponse = stockDubboService.increaseStock(commodityDTO);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(order.getUserId());
        accountDTO.setAmount(new BigDecimal(order.getAmount()));
        ObjectResponse objectResponse = accountDubboService.increaseAccount(accountDTO);

        if (stockResponse.getStatus() != 200 || objectResponse.getStatus() != 200) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        baseMapper.deleteOrder(orderDTO.getOrderNo());
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }

    private String getOrderTime() {
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        long timestamp = System.currentTimeMillis();
        String todayAsString = df.format(timestamp);
        return todayAsString;
    }
}
