package org.neu.cs6650.koi.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.stock.entity.TStock;

public interface ITStockService extends IService<TStock> {

    ObjectResponse decreaseStock(CommodityDTO commodityDTO);

    ObjectResponse increaseStock(CommodityDTO commodityDTO);

    ObjectResponse getAllStocks();

    ObjectResponse getStock(CommodityDTO commodityDTO);
}

