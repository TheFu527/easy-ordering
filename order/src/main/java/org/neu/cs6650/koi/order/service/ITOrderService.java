package org.neu.cs6650.koi.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.order.entity.TOrder;

public interface ITOrderService extends IService<TOrder> {

    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}
