package org.neu.cs6650.koi.order;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "org.neu.cs6650.koi.order")
@EnableDiscoveryClient
@MapperScan({"org.neu.cs6650.koi.order.mapper"})
@EnableDubbo(scanBasePackages = "org.neu.cs6650.koi.order")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}

