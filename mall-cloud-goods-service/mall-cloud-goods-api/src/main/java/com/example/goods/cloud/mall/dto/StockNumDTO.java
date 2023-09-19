package com.example.goods.cloud.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * ClassName: StockNumDTO
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 16:18   @Version 1.0        描述
 */
public class StockNumDTO {
    @ApiModelProperty(value = "商品Id")
    private Long goodsId;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsCount;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
}
