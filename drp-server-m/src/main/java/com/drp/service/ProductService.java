package com.drp.service;

import com.drp.entity.PProductSkuEntity;
import com.drp.vo.ShoppingCartItemVO;
import com.drp.vo.SkuDetailVO;
import com.drp.vo.SpuDetailVO;
import com.drp.vo.SpuSearchVO;

import java.util.List;
import java.util.Map;

public interface ProductService {

    // 获取spu列表
    public Map<String,Object> getProductList(SpuSearchVO vo);

    // 获取spuDetail
    public SpuDetailVO getSpuDetail(Integer spuId, Integer distributorId);

    // 获取已关注商品列表
    public Map<String,Object>getCollectionProductList(Integer distributorId, Integer pageNum, Integer pageSize);

    // 取消关注
    public Integer cancelCollectionProduct(Integer distributorId, List<Integer> ids);

    // 关注
    public Integer addCollectionProduct(Integer distributorId, List<Integer> spuIds);

    // 获取购物车列表
    public List<ShoppingCartItemVO> getShoppingCartList(Integer distributorId, Integer type);

    // 更新购物车
    public Integer updateShoppingCartList(List<ShoppingCartItemVO> shoppingCartItemVOList, Integer distributorId);

    // 修改库存
    public Integer updateSkuLockStock(List skuList);


}
