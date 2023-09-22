package com.example.goods.cloud.mall.config;

import com.alibaba.cloud.seata.web.SeataHandlerInterceptor;
import com.alibaba.cloud.sentinel.SentinelProperties;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.example.goods.cloud.mall.config.handler.TokenToAdminUserMethodArgumentResolver;
import com.example.goods.cloud.mall.config.handler.TokenToMallUserMethodArgumentResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: GoodsServiceWebMvcConfigurer
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/20 10:19   @Version 1.0        描述
 */
@Configuration
public class GoodsServiceWebMvcConfigurer extends WebMvcConfigurationSupport {

    private static final Logger log = LoggerFactory.getLogger(GoodsServiceWebMvcConfigurer.class);

    @Autowired
    private SentinelProperties sentinelProperties;

    @Autowired
    private Optional<SentinelWebInterceptor> sentinelWebInterceptorOptional;

    @Autowired
    @Lazy
    private TokenToAdminUserMethodArgumentResolver tokenToAdminUserMethodArgumentResolver;

    @Autowired
    @Lazy
    private TokenToMallUserMethodArgumentResolver tokenToMallUserMethodArgumentResolver;

    /**
     * 自定义参数预处理
     * note 请求进入Controller方法前会被该方法截取，调用其中添加的自定义处理方法对参数进行处理，处理好的参数再传给Controller中的方法
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenToAdminUserMethodArgumentResolver);
        argumentResolvers.add(tokenToMallUserMethodArgumentResolver);
    }

    /**
     * 静态资源文件映射配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**") //自定义路径访问（实际上会创建一个ResourceHandlerRegistration对象）
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui") //映射到jar包内的静态文件
                .resourceChain(false); //资源链
    }

    /**
     * 自定义拦截器配置
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SeataHandlerInterceptor()).addPathPatterns("/**");
        if (this.sentinelWebInterceptorOptional.isPresent()) {
            SentinelProperties.Filter filterConfig = this.sentinelProperties.getFilter();
            registry.addInterceptor((HandlerInterceptor) this.sentinelWebInterceptorOptional.get())
                    .order(filterConfig.getOrder()) //拦截器优先级
                    .addPathPatterns(filterConfig.getUrlPatterns());
            log.info("[Sentinel Starter] register SentinelWebInterceptor with urlPatterns: {}", filterConfig.getUrlPatterns());
        }
    }
}
