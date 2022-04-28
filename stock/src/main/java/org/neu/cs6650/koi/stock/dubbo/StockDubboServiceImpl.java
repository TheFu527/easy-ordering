package org.neu.cs6650.koi.stock.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.dubbo.StockDubboService;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.stock.service.ITStockService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0", protocol = "${dubbo.protocol.id}", application = "${dubbo.application.id}",
    registry = "${dubbo.registry.id}", timeout = 3000)
@Slf4j
public class StockDubboServiceImpl implements StockDubboService {

    @Autowired
    private ITStockService stockService;

    @Override
    public ObjectResponse decreaseStock(CommodityDTO commodityDTO) {
        log.info("Global transaction id: " + RootContext.getXID());
        log.info("decreaseStock: {}", commodityDTO.toString());
        return stockService.decreaseStock(commodityDTO);
    }

    @Override
    public ObjectResponse increaseStock(CommodityDTO commodityDTO) {
        log.info("Global transaction id: " + RootContext.getXID());
        log.info("increaseStock: {}", commodityDTO.toString());
        return stockService.increaseStock(commodityDTO);
    }

    @Override
    public ObjectResponse getAllStocks() {
        log.info("Global transaction id: " + RootContext.getXID());
        log.info("getAllStocks");
        return stockService.getAllStocks();
    }

    @Override
    public ObjectResponse getStock(CommodityDTO commodityDTO) {
        log.info("Global transaction id: " + RootContext.getXID());
        log.info("getStock: {}", commodityDTO.toString());
        return stockService.getStock(commodityDTO);
    }
}
