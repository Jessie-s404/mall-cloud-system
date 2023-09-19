package com.example.common.cloud.mall.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: MallUserToken
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 9:50   @Version 1.0        描述
 */
@Data
public class MallUserToken implements Serializable {
    private Long userId;

    private String token;
}
