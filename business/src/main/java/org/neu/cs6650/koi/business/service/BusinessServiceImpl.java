package org.neu.cs6650.koi.business.service;

import com.alibaba.dubbo.config.annotation.Reference;
import io.seata.core.context.RootContext;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.dubbo.OrderDubboService;
import org.neu.cs6650.koi.common.dubbo.StockDubboService;
import org.neu.cs6650.koi.common.enums.RspStatusEnum;
import org.neu.cs6650.koi.common.exception.DefaultException;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Reference(version = "1.0.0")
    private StockDubboService stockDubboService;

    @Reference(version = "1.0.0")
    private OrderDubboService orderDubboService;

    private boolean flag;

    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata")
    public ObjectResponse handleBusiness(BusinessDTO businessDTO) {
        System.out.println("Start global transaction, XID: " + RootContext.getXID());
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

//        if (!flag) {
//            throw new RuntimeException("After the test throws an exception, the distributed transaction is rolled back!");
//        }

        if (stockResponse.getStatus() != 200 || response.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setData(response.getData());
        return objectResponse;
    }
}
