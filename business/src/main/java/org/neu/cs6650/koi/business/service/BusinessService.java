package org.neu.cs6650.koi.business.service;

import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;

public interface BusinessService {

    ObjectResponse handleBusiness(BusinessDTO businessDTO);

    ObjectResponse getAllStocks();

    ObjectResponse getStock(CommodityDTO commodityDTO);

    ObjectResponse getOrderById(BusinessDTO businessDTO);
}
