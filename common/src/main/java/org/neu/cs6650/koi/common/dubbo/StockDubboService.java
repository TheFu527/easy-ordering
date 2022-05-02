package org.neu.cs6650.koi.common.dubbo;

import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;

public interface StockDubboService {

    ObjectResponse decreaseStock(CommodityDTO commodityDTO);

    ObjectResponse increaseStock(CommodityDTO commodityDTO);

    ObjectResponse getAllStocks();

    ObjectResponse getStock(CommodityDTO commodityDTO);
}
