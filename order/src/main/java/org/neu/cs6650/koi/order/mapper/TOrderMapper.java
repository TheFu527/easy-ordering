package org.neu.cs6650.koi.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.neu.cs6650.koi.order.entity.TOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TOrderMapper extends BaseMapper<TOrder> {

    void createOrder(@Param("order") TOrder order);

    TOrder getOrderByOId(@Param("orderNo") String orderNo);

    List<TOrder> getOrderByUId(@Param("userId") String userId);

    void deleteOrder(@Param("orderNo") String orderNo);
}
