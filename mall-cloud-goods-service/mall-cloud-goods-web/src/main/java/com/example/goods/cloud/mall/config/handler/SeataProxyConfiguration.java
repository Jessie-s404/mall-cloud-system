package com.example.goods.cloud.mall.config.handler;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.checkerframework.checker.units.qual.C;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * ClassName: SeataProxyConfiguration
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 14:56   @Version 1.0        描述
 */
@Configuration
public class SeataProxyConfiguration {

    //创建Druid数据源
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() { return new DruidDataSource(); }

    //创建DataSource数据源代理
    @Bean("dataSource")
    @Primary
    public DataSource dataSourceDelegation(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    /**
     * 解决druid 日志报错：discard long time none received connection:xxx
     */
    @PostConstruct
    public void setProperties() { System.setProperty("druid.mysql.usePingMethod", "false"); }
}
