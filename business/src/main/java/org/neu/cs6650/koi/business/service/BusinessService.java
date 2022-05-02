package org.neu.cs6650.koi.business.service;

import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;

public interface BusinessService {

    ObjectResponse handleBusiness(BusinessDTO businessDTO);

    ObjectResponse getAllStocks();

    ObjectResponse getStock(CommodityDTO commodityDTO);

    ObjectResponse getOrderByOId(OrderDTO orderDTO);

    ObjectResponse getOrderByUId(AccountDTO accountDTO);

    ObjectResponse deleteOrder(OrderDTO orderDTO);
}
