package org.neu.cs6650.koi.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.order.entity.TOrder;

import java.util.List;

public interface ITOrderService extends IService<TOrder> {

    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);

    ObjectResponse<OrderDTO> getOrderByOId(OrderDTO orderDTO);

    ObjectResponse getOrderByUId(AccountDTO accountDTO);

    ObjectResponse deleteOrder(OrderDTO orderDTO);
}
