package com.example.goods.cloud.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.goods.cloud.mall.entity.GoodsCategory;
import com.example.goods.cloud.mall.entity.MallGoods;

/**
 * ClassName: MallGoodsService
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 18:05   @Version 1.0        描述
 */
public interface MallGoodsService extends IService<MallGoods> {

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveNewBeeMallGoods(MallGoods goods);
}
