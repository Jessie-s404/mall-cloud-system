package com.example.goods.cloud.mall.config.handler;

import feign.Logger;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: OpenFeignConfiguration
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 14:53   @Version 1.0        描述
 */
@Configuration
public class OpenFeignConfiguration {
    public Logger.Level openFeignLogLevel() {
        // 设置OpenFeign日志级别
        return Logger.Level.FULL;
    }
}
