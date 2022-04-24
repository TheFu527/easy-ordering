package org.neu.cs6650.koi.account;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "org.neu.cs6650.koi.account")
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan({"org.neu.cs6650.koi.account.mapper"})
@EnableDubbo(scanBasePackages = "org.neu.cs6650.koi.account")
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}

