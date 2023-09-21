package com.example.goods.cloud.mall.controller;

import com.example.common.cloud.mall.dto.Result;
import com.example.common.cloud.mall.dto.ResultGenerator;
import com.example.common.cloud.mall.exception.AppException;
import com.example.goods.cloud.mall.service.MallGoodsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: MallGoodsController
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/20 17:02   @Version 1.0        描述
 */
@RestController
@Api(value = "v1", tags = "商城商品相关接口")
@RequestMapping("/goods/mall")
@Slf4j
public class MallGoodsController {

    @Resource
    private MallGoodsService mallGoodsService;

    @GetMapping("/test1")
    public Result<String> test1() throws BindException {
        throw new BindException(1, "BindException");
    }

    @GetMapping("/test2")
    public Result<String> test2() throws AppException {
        AppException.fail("AppException");
        return ResultGenerator.genSuccessResult("test2");
    }

    @GetMapping("/test3")
    public Result<String> test3() throws Exception {
        int i = 1/0;
        return ResultGenerator.genSuccessResult("test2");
    }
}
