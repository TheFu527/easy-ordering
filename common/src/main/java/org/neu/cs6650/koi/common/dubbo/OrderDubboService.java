package org.neu.cs6650.koi.common.dubbo;

import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;

import java.util.List;

public interface OrderDubboService {

    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);

    ObjectResponse<OrderDTO> getOrderByOId(OrderDTO orderDTO);

    ObjectResponse<List<OrderDTO>> getOrderByUId(AccountDTO accountDTO);

    ObjectResponse<Object> deleteOrder(OrderDTO orderDTO);
}
