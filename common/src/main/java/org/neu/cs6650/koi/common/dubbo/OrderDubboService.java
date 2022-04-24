package org.neu.cs6650.koi.common.dubbo;

import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;

public interface OrderDubboService {

    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}
