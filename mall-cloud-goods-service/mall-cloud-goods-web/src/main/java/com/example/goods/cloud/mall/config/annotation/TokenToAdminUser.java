package com.example.goods.cloud.mall.config.annotation;

import java.lang.annotation.*;

/**
 * ClassName: TokenToAdminUser
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 16:12   @Version 1.0        描述
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToAdminUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default "adminUser";
}
