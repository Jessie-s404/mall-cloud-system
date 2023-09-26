package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import zipkin2.server.internal.EnableZipkinServer;

import javax.sql.DataSource;

/**
 * ClassName: ZipkinServerApplication9411
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/25 9:52   @Version 1.0        描述
 */
@SpringBootApplication
@EnableZipkinServer //开启Zipkin Server功能
public class ZipkinServerApplication9411 {
    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication9411.class, args);
    }
}
