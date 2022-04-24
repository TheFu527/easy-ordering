package org.neu.cs6650.koi.business;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "org.neu.cs6650.koi.business")
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "org.neu.cs6650.koi.business")
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }

}

