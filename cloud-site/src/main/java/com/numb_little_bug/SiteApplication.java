package com.numb_little_bug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient  // 开启服务注册与发现，可写可不写
@EnableFeignClients
public class SiteApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(SiteApplication.class, args);
    }
}