package com.example.gateway.cloud.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: PolymerizeSwaggerProvider
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 15:42   @Version 1.0        描述
 */
@Primary
@Component
public class PolymerizeSwaggerProvider implements SwaggerResourcesProvider {

    /**
     * Swagger Doc的URL后缀
     */
    public static final String API_DOCS_URL = "/v3/api-docs";

    @Autowired
    private GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //需要聚合的路由配置
        routes.add("user-service-swagger-route");//用户文档路由
        routes.add("goods-service-swagger-route");//商品文档路由
        routes.add("recommend-service-swagger-route");//推荐文档路由
        routes.add("order-service-swagger-route");//订单文档路由
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                                predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                        .replace("/**", API_DOCS_URL)))));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String url) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(url);
        swaggerResource.setSwaggerVersion("3.0");
        return swaggerResource;
    }
}
