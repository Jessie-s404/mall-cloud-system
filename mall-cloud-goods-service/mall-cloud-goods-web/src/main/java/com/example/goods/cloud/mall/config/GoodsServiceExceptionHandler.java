package com.example.goods.cloud.mall.config;

import com.example.common.cloud.mall.dto.Result;
import com.example.common.cloud.mall.enums.ServiceResultEnum;
import com.example.common.cloud.mall.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * ClassName: GoodsServiceExceptionHandler
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 18:02   @Version 1.0        描述
 */
@RestControllerAdvice
public class GoodsServiceExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GoodsServiceExceptionHandler.class);

    @ExceptionHandler(BindException.class)
    public Object bindException(BindException e) {
        log.error("GoodsServiceExceptionHandler:", e);
        Result result = new Result();
        result.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object bindException(MethodArgumentNotValidException e) {
        log.error("GoodsServiceExceptionHandler:", e);
        Result result = new Result();
        result.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req) {
        log.error("GoodsServiceExceptionHandler:", e);
        Result result = new Result();
        result.setResultCode(500);
        //区分是否为自定义异常
        if (e instanceof AppException) {
            result.setMessage(e.getMessage());
            if (e.getMessage().equals(ServiceResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ServiceResultEnum.ADMIN_TOKEN_EXPIRE_ERROR.getResult())) {
                result.setResultCode(419);
            }
            if (e.getMessage().equals(ServiceResultEnum.NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult())) {
                result.setResultCode(416);
            }
            return result;
        } else {
            //未知异常
            return Result.error();
        }
    }
}
