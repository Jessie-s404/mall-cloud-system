package com.example.goods.cloud.mall.config.handler;

import com.example.common.cloud.mall.dto.Result;
import com.example.common.cloud.mall.enums.ServiceResultEnum;
import com.example.common.cloud.mall.exception.AppException;
import com.example.common.cloud.mall.pojo.MallUserToken;
import com.example.goods.cloud.mall.config.annotation.TokenToMallUser;
import com.example.user.cloud.mall.dto.MallUserDTO;
import com.example.user.cloud.mall.openfeign.MallCloudUserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * ClassName: TokenToMallUserMethodArgumentResolver
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/20 16:41   @Version 1.0        描述
 */
@Component
public class TokenToMallUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MallCloudUserServiceFeign mallCloudUserServiceFeign;

    public TokenToMallUserMethodArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToMallUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToMallUser.class) instanceof TokenToMallUser) {
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                Result<MallUserDTO> result = mallCloudUserServiceFeign.getMallUserByToken(token);
                if (result == null || result.getResultCode() != 200 || result.getData() == null) {
                    AppException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }
                MallUserToken mallUserToken = new MallUserToken();
                mallUserToken.setToken(token);
                mallUserToken.setUserId(result.getData().getUserId());
                return mallUserToken;
            } else {
                AppException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }
}
