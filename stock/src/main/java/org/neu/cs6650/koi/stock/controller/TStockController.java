package org.neu.cs6650.koi.stock.controller;

import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.stock.service.ITStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
@Slf4j
public class TStockController {

    @Autowired
    private ITStockService stockService;

    @PostMapping("dec_stock")
    ObjectResponse decreaseStock(@RequestBody CommodityDTO commodityDTO) {
        log.info("decreaseStock: {}", commodityDTO.toString());
        return stockService.decreaseStock(commodityDTO);
    }
}

