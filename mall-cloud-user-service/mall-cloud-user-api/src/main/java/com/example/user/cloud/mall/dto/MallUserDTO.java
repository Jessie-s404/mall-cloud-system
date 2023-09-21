package com.example.user.cloud.mall.dto;

import java.io.Serializable;

/**
 * ClassName: MallUserDTO
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/20 16:48   @Version 1.0        描述
 */
public class MallUserDTO implements Serializable {
    private Long userId;

    private String nickName;

    private String loginName;

    private String introduceSign;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIntroduceSign() {
        return introduceSign;
    }

    public void setIntroduceSign(String introduceSign) {
        this.introduceSign = introduceSign;
    }
}
