package org.neu.cs6650.koi.order.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import io.seata.core.context.RootContext;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.dubbo.OrderDubboService;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.order.service.ITOrderService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0", protocol = "${dubbo.protocol.id}", application = "${dubbo.application.id}",
    registry = "${dubbo.registry.id}", timeout = 3000)
public class OrderDubboServiceImpl implements OrderDubboService {

    @Autowired
    private ITOrderService orderService;

    @Override
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        System.out.println("Global transaction id: " + RootContext.getXID());
        return orderService.createOrder(orderDTO);
    }
}
