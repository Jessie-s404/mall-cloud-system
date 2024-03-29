package com.example.goods.cloud.mall.config.handler;

import com.example.common.cloud.mall.dto.Result;
import com.example.common.cloud.mall.enums.ServiceResultEnum;
import com.example.common.cloud.mall.exception.AppException;
import com.example.goods.cloud.mall.config.annotation.TokenToAdminUser;
import com.example.goods.cloud.mall.entity.LoginAdminUser;
import com.example.user.cloud.mall.openfeign.MallCloudUserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.LinkedHashMap;

/**
 * ClassName: TokenToAdminUserMethodArgumentResolver
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/20 10:27   @Version 1.0        描述
 */
@Component
public class TokenToAdminUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MallCloudUserServiceFeign mallCloudUserServiceFeign;

    public TokenToAdminUserMethodArgumentResolver() {

    }

    /**
     * note 判断Controller层中的参数是否满足条件，满足条件则执行resolveArgument方法，不满足则跳过
     *
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToAdminUser.class)) {
            return true;
        }
        return false;
    }

    /**
     * note 只有在supportParameter方法返回true的情况下才会被调用，用于处理一些业务，将返回值赋值给Controller层中的这个参数
     *      (简而言之，这个接口实现了对Controller层中的参数的提取和修改)
     *
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToAdminUser.class) instanceof TokenToAdminUser) {
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                // 通过用户中心获取用户信息
                Result result = mallCloudUserServiceFeign.getAdminUserByToken(token);

                if (result == null || result.getResultCode() != 200 || result.getData() == null) {
                    AppException.fail(ServiceResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult());
                }

                LinkedHashMap resultData = (LinkedHashMap) result.getData();

                // 将返回的字段封装到LoginAdminUser对象中
                LoginAdminUser loginAdminUser = new LoginAdminUser();
                loginAdminUser.setAdminUserId(Long.valueOf(resultData.get("adminUserId").toString()));
                loginAdminUser.setLoginUserName(resultData.get("loginUserName").toString());
                loginAdminUser.setNickName(resultData.get("nickName").toString());
                loginAdminUser.setLocked(Byte.valueOf(resultData.get("locked").toString()));
                return loginAdminUser;
            } else {
                AppException.fail(ServiceResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }
}
