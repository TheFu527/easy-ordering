package org.neu.cs6650.koi.stock.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.enums.RspStatusEnum;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.stock.entity.TStock;
import org.neu.cs6650.koi.stock.mapper.TStockMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TStockServiceImpl extends ServiceImpl<TStockMapper, TStock> implements ITStockService {

    @Override
    public ObjectResponse decreaseStock(CommodityDTO commodityDTO) {
        ObjectResponse<Object> response = new ObjectResponse<>();
        TStock stock = baseMapper.getStock(commodityDTO.getCommodityCode());
        if (stock.getCount() < commodityDTO.getCount()) {
            response.setStatus(RspStatusEnum.STOCK_LOW.getCode());
            response.setMessage(RspStatusEnum.STOCK_LOW.getMessage());
            return response;
        }
        baseMapper.decreaseStock(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }

    @Override
    public ObjectResponse increaseStock(CommodityDTO commodityDTO) {
        int stock = baseMapper.increaseStock(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        ObjectResponse<Object> response = new ObjectResponse<>();

        if (stock > 0) {
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.FAIL.getCode());
        response.setMessage(RspStatusEnum.FAIL.getMessage());
        return response;
    }

    @Override
    public ObjectResponse getAllStocks() {
        List<TStock> stockList = baseMapper.getAllStocks();
        ObjectResponse<Object> response = new ObjectResponse<>();
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setData(stockList);
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }

    @Override
    public ObjectResponse getStock(CommodityDTO commodityDTO) {
        TStock stock = baseMapper.getStock(commodityDTO.getCommodityCode());
        ObjectResponse<Object> response = new ObjectResponse<>();
        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setData(stock);
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }
}
