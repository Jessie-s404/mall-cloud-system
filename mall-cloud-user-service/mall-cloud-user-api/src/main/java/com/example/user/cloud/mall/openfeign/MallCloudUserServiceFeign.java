package com.example.user.cloud.mall.openfeign;

import com.example.common.cloud.mall.dto.Result;
import com.example.user.cloud.mall.dto.MallUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: MallCloudUserServiceFeign
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/20 10:30   @Version 1.0        描述
 */
@FeignClient(value = "mall-cloud-user-service", path = "/users")
public interface MallCloudUserServiceFeign {

    @GetMapping(value = "/admin/{token}")
    Result getAdminUserByToken(@PathVariable(value = "token") String token);

    @GetMapping(value = "/mall/getDetailByToken")
    Result<MallUserDTO> getMallUserByToken(@RequestParam(value = "token") String token);

}
