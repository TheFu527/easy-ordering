package org.neu.cs6650.koi.order.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.dubbo.OrderDubboService;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.order.service.ITOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0", protocol = "${dubbo.protocol.id}", application = "${dubbo.application.id}",
    registry = "${dubbo.registry.id}", timeout = 3000)
@Slf4j
public class OrderDubboServiceImpl implements OrderDubboService {

    @Autowired
    private ITOrderService orderService;

    @Override
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        log.info("Global transaction id: {}", RootContext.getXID());
        log.info("createOrder: {}", orderDTO.toString());
        return orderService.createOrder(orderDTO);
    }

    @Override
    public ObjectResponse<OrderDTO> getOrderByOId(OrderDTO orderDTO) {
        log.info("getOrderByOId: {}", orderDTO.toString());
        return orderService.getOrderByOId(orderDTO);
    }

    @Override
    public ObjectResponse<List<OrderDTO>> getOrderByUId(AccountDTO accountDTO) {
        log.info("getOrderByUId: {}", accountDTO.toString());
        return orderService.getOrderByUId(accountDTO);
    }

    @Override
    public ObjectResponse<Object> deleteOrder(OrderDTO orderDTO) {
        log.info("deleteOrder: {}", orderDTO.toString());
        return orderService.deleteOrder(orderDTO);
    }
}
