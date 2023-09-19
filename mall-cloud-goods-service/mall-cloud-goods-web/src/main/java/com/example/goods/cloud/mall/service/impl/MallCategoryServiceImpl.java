package com.example.goods.cloud.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.cloud.mall.enums.ServiceResultEnum;
import com.example.goods.cloud.mall.dao.GoodsCategoryMapper;
import com.example.goods.cloud.mall.entity.GoodsCategory;
import com.example.goods.cloud.mall.service.MallCategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * ClassName: MallCategoryServiceImpl
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/19 16:20   @Version 1.0        描述
 */
@Service
public class MallCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements MallCategoryService {

    @Override
    public String saveCategory(GoodsCategory goodsCategory) {
        GoodsCategory category = selectByLevelAndName(goodsCategory);
        if (Objects.nonNull(category)) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        if (this.save(goodsCategory)) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateGoodsCategory(GoodsCategory goodsCategory) {
        GoodsCategory category = this.getById(goodsCategory.getCategoryId());
        if (Objects.isNull(category)) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        GoodsCategory otherCategory = selectByLevelAndName(goodsCategory);
        if (Objects.nonNull(otherCategory) && !otherCategory.getCategoryId().equals(goodsCategory.getCategoryId())) {
            //存在同名的其他种类，不能继续修改
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        goodsCategory.setUpdateTime(new Date());
        if (this.updateById(goodsCategory)) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public GoodsCategory getGoodsCategoryById(Long id) {
        return this.getById(id);
    }

    @Override
    public Boolean deleteBatch(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //删除分类数据
        return this.deleteBatch(ids);
    }

    @Override
    public List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        return this.list(new LambdaQueryWrapper<GoodsCategory>()
                .in(GoodsCategory::getParentId, parentIds)
                .eq(GoodsCategory::getCategoryLevel, categoryLevel));
    }

    private GoodsCategory selectByLevelAndName(GoodsCategory goodsCategory) {
        return this.getOne(new LambdaQueryWrapper<GoodsCategory>()
                .eq(GoodsCategory::getCategoryLevel, goodsCategory.getCategoryLevel())
                .eq(GoodsCategory::getCategoryName, goodsCategory.getCategoryName()));
    }
}
