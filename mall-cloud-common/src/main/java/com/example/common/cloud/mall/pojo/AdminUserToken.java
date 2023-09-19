package com.example.common.cloud.mall.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: AdminUserToken
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 9:49   @Version 1.0        描述
 */
@Data
public class AdminUserToken implements Serializable {
    private Long adminUserId;

    private String token;
}
