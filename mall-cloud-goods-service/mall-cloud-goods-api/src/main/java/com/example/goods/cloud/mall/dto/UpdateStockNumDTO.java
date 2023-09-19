package com.example.goods.cloud.mall.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * ClassName: UpdateStockNumDTO
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 16:19   @Version 1.0        描述
 */
public class UpdateStockNumDTO {

    @ApiModelProperty(value = "商品库存列表")
    private List<StockNumDTO> stockNumDTOS;

    public List<StockNumDTO> getStockNumDTOS() {
        return stockNumDTOS;
    }

    public void setStockNumDTOS(List<StockNumDTO> stockNumDTOS) {
        this.stockNumDTOS = stockNumDTOS;
    }
}
