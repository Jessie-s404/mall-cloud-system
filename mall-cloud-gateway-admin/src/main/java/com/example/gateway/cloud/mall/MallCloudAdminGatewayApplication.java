package com.example.gateway.cloud.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: MallCloudAdminGatewayApplication
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 10:03   @Version 1.0        描述
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MallCloudAdminGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCloudAdminGatewayApplication.class);
    }
}
