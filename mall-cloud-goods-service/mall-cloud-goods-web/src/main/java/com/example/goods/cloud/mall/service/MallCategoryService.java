package com.example.goods.cloud.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.goods.cloud.mall.entity.GoodsCategory;

import java.util.List;

/**
 * ClassName: MallCategoryService
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 14:06   @Version 1.0        描述
 */
public interface MallCategoryService extends IService<GoodsCategory> {

    String saveCategory(GoodsCategory goodsCategory);

    String updateGoodsCategory(GoodsCategory goodsCategory);

    GoodsCategory getGoodsCategoryById(Long id);

    Boolean deleteBatch(Long[] ids);

    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);
}
