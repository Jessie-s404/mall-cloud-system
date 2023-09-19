package com.example.goods.cloud.mall.openfeign;

import com.example.common.cloud.mall.dto.Result;
import com.example.goods.cloud.mall.dto.MallGoodsDTO;
import com.example.goods.cloud.mall.dto.UpdateStockNumDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName: MallCloudGoodsServiceFeign
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 16:20   @Version 1.0        描述
 */
@FeignClient(value = "mall-cloud-goods-service", path = "/goods")
public interface MallCloudGoodsServiceFeign {

    @GetMapping(value = "/admin/goodsDetail")
    Result<MallGoodsDTO> getGoodsDetail(@RequestParam(value = "goodsId") Long goodsId);

    @GetMapping(value = "/admin/listByGoodsIds")
    Result<List<MallGoodsDTO>> listByGoodsIds(@RequestParam(value = "goodsIds") List<Long> goodsIds);

    @PutMapping(value = "/admin/updateStock")
    Result<Boolean> updateStock(@RequestBody UpdateStockNumDTO updateStockNumDTO);
}
