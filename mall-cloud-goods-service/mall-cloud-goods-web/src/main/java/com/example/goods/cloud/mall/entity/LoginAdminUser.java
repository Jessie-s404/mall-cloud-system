package com.example.goods.cloud.mall.entity;

import lombok.Data;

/**
 * ClassName: LoginAdminUser
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 9:11   @Version 1.0        描述
 */
@Data
public class LoginAdminUser {
    private Long adminUserId;

    private String loginUserName;

    private String loginPassword;

    private String nickName;

    private Byte locked;
}
