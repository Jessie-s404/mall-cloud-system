package com.example.goods.cloud.mall.entity;

import lombok.Data;

import java.util.List;

/**
 * ClassName: UpdateStockNumDTO
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 16:17   @Version 1.0        描述
 */
@Data
public class UpdateStockNumDTO {

    private List<StockNumDTO> stockNumDTOS;

    public List<StockNumDTO> getStockNumDTOS() { return stockNumDTOS; }

    public void setStockNumDTOS(List<StockNumDTO> stockNumDTOS) { this.stockNumDTOS = stockNumDTOS; }
}
