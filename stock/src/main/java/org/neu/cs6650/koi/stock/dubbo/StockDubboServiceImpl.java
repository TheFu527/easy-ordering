package org.neu.cs6650.koi.stock.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import io.seata.core.context.RootContext;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.dubbo.StockDubboService;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.stock.service.ITStockService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0", protocol = "${dubbo.protocol.id}", application = "${dubbo.application.id}",
    registry = "${dubbo.registry.id}", timeout = 3000)
public class StockDubboServiceImpl implements StockDubboService {

    @Autowired
    private ITStockService stockService;

    @Override
    public ObjectResponse decreaseStock(CommodityDTO commodityDTO) {
        System.out.println("Global transaction id: " + RootContext.getXID());
        return stockService.decreaseStock(commodityDTO);
    }
}
