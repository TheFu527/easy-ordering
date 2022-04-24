package org.neu.cs6650.koi.stock;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "org.neu.cs6650.koi.stock")
@EnableDiscoveryClient
@MapperScan({"org.neu.cs6650.koi.stock.mapper"})
@EnableDubbo(scanBasePackages = "org.neu.cs6650.koi.stock")
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }

}

