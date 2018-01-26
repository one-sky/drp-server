package com.drp.repository;

import com.drp.entity.*;
import com.drp.vo.*;

import java.util.List;

public interface ProductRepository {
    public List<SpuSearchVO> getProductList(SpuSearchVO vo);

    // 根据spuId获取该spu下的促销sku 或者根据spuId与skuId获取价格
    public List<SkuPriceDetailVO> getPriceListPromotion(Integer spuId, Integer skuId);

    // 根据分销商等级获取sku价格列表
    public List<SkuPriceDetailVO> getPriceList(List<Integer> skuIdList, Integer distributorId);

    // 根据分销商获取特殊商品
    public List<SkuPriceDetailVO> getPriceListByDistributorId(List<Integer> skuIdList, Integer distributorId);

    // 获取spu的artilce列表
    public List<PProductArticleEntity> getArticleList(Integer spuId, Integer distributorId);

    // 获取已关注商品列表
    public List<CollectProductVO> getCollectionProductList(Integer distributorId,  Integer pageSize, Integer startIndex);

    // 取消关注
    public Integer cancelCollectionProduct(Integer distributorId, List<Integer> ids);

    // 关注
    public Integer addCollectionProduct(Integer distributorId, List<Integer> spuIds, DCollectionEntity entity);

    // 查找价格
    public List<List<SkuPriceDetailVO>> searchPrice(List<Integer> skuIdList, Integer distributorId);

    public List<ShoppingCartItemVO> getBasicShoppingCartList(Integer distributorId, Integer type);

    // 更新购物车
    public Integer updateShoppingCart(PCartEntity cart);

    // 插入购物车
    public Integer insertShoppingCart(PCartEntity cart);

    // 删除购物车中一条
    public Integer deleteShoppingCart(PCartEntity cart);

    // 修改库存
    public Integer updateSkuLockStock(List skuList);
}
