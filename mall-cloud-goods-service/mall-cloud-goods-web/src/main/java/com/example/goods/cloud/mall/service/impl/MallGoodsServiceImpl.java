package com.example.goods.cloud.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.cloud.mall.enums.MallCategoryLevelEnum;
import com.example.common.cloud.mall.enums.ServiceResultEnum;
import com.example.goods.cloud.mall.dao.GoodsCategoryMapper;
import com.example.goods.cloud.mall.dao.MallGoodsMapper;
import com.example.goods.cloud.mall.entity.GoodsCategory;
import com.example.goods.cloud.mall.entity.MallGoods;
import com.example.goods.cloud.mall.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: MallGoodsServiceImpl
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/18 18:06   @Version 1.0        描述
 */
@Service
public class MallGoodsServiceImpl extends ServiceImpl<MallGoodsMapper, MallGoods> implements MallGoodsService {

    @Autowired
    private MallGoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public String saveNewBeeMallGoods(MallGoods goods) {
        GoodsCategory goodsCategory = goodsCategoryMapper.selectById(goods.getGoodsCategoryId());
        // 分类不存在或者不是三级分类。则该参数字段异常
        if (goodsCategory == null || goodsCategory.getCategoryLevel().intValue() != MallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ServiceResultEnum.GOODS_CATEGORY_ERROR.getResult();
        }
        List<MallGoods> goodsList = this.list(new QueryWrapper<MallGoods>()
                .eq("goods_name", goods.getGoodsName())
                .eq("goods_category_id", goods.getGoodsCategoryId()));
        if (CollectionUtils.isNotEmpty(goodsList)) {
            return ServiceResultEnum.SAME_GOODS_EXIST.getResult();
        }
        if (this.save(goods)) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }
}
