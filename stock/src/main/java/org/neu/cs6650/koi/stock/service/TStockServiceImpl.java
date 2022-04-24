package org.neu.cs6650.koi.stock.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.enums.RspStatusEnum;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.stock.entity.TStock;
import org.neu.cs6650.koi.stock.mapper.TStockMapper;
import org.springframework.stereotype.Service;

@Service
public class TStockServiceImpl extends ServiceImpl<TStockMapper, TStock> implements ITStockService {

    @Override
    public ObjectResponse decreaseStock(CommodityDTO commodityDTO) {
        int stock = baseMapper.decreaseStock(commodityDTO.getCommodityCode(), commodityDTO.getCount());
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
}
