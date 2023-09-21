package com.example.goods.cloud.mall.config;

import com.example.common.cloud.mall.pojo.AdminUserToken;
import com.example.common.cloud.mall.pojo.MallUserToken;
import com.example.goods.cloud.mall.entity.LoginAdminUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GoodsServiceSwagger3Config
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 14:24   @Version 1.0        描述
 */
@Configuration
@EnableOpenApi
public class GoodsServiceSwagger3Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())// 用于定义api文档汇总信息
                .ignoredParameterTypes(AdminUserToken.class, LoginAdminUser.class, MallUserToken.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.goods.cloud.mall.controller"))// 指定controller包
                .paths(PathSelectors.any())// 所有controller
                .build()
                .globalRequestParameters(getGlobalRequestParameters());
    }

    //生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("登录认证token")
                .required(false)// 非必传
                .in(ParameterType.HEADER) //请求头中的参数，其它类型可以点进ParameterType类中查看
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build());
        return parameters;
    }

    /**
     * api文档属性构建器
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mall-cloud-goods-service接口文档")// 文档页标题
                .description("商品中心-swagger接口文档")// 详细信息
                .version("2.0")// 文档版本号
                .build();
    }
}
