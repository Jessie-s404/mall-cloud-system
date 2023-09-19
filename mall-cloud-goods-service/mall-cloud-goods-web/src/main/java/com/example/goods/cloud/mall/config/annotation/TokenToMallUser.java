package com.example.goods.cloud.mall.config.annotation;

import java.lang.annotation.*;

/**
 * ClassName: TokenToMallUser
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 15:09   @Version 1.0        描述
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToMallUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default "user";
}
