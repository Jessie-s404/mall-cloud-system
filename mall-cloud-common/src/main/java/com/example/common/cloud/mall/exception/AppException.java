package com.example.common.cloud.mall.exception;

/**
 * ClassName: AppException
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 9:46   @Version 1.0        描述
 */
public class AppException extends RuntimeException {

    public AppException() {

    }

    public AppException(String message) {super(message);}

    /**
     * 抛出一个异常
     *
     * @param message
     */
    public static void fail(String message) {throw new AppException(message);}
}
