package com.example.goods.cloud.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: MallCloudGoodsServiceApplication
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 16:29   @Version 1.0        描述
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.goods.cloud.mall.dao")
@EnableFeignClients()
public class MallCloudGoodsServiceApplication {
    public static void main(String[] args) {
        // 设置系统全局变量
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(MallCloudGoodsServiceApplication.class);
    }
}
