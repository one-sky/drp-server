package com.drp.service;

import com.drp.entity.*;
import com.drp.vo.AgentBrandVO;
import com.drp.vo.ManageUserVO;
import com.drp.vo.OrderSearchVO;

import java.util.List;
import java.util.Map;

public interface ManageService {
    public Map<String, Object>  getUserList(ManageUserVO entity, Integer pageNum, Integer pageSize);

    public Integer updatePoint(Integer userId, Integer point, String reason);

    public Map<String, Object>  getBrandList(RBrandEntity entity, Integer pageNum, Integer pageSize);

    public Integer saveBrand(RBrandEntity entity);

    public Integer deleteBrand(Integer brandId);

    public Map<String, Object>  getAgentBrandList(AgentBrandVO entity, Integer pageNum, Integer pageSize);

    public Integer updateAgentStatus(DAgentBrandEntity entity);

    public Integer saveCategory(PCategoryEntity entity);

    public Integer deleteCategory(PCategoryEntity entity);

    public Integer saveAttr(PCategoryAttrEntity entity);

    public Integer deleteAttr(PCategoryAttrEntity entity);

    public Integer saveAttrValue(PAttrValueEntity entity);

    public Integer deleteAttrValue(PAttrValueEntity entity);

    public Integer saveProduct(PProductCategoryEntity entity);

    public Integer saveSku(PProductSkuEntity entity, List<PProductQuantityEntity> priceList);

    public Integer savePriceList(Integer skuId, List<PProductQuantityEntity> entity);

    public Integer updateOrder(OOrderEntity entity);

    public Integer savePromotion(PProductPromotionEntity entity);

    public Integer savePromotionPriceList(Integer promotionId, Integer skuId, List<PPromoteProductEntity> entity);

    public Integer saveSpecialPriceList(Integer distributorId, Integer skuId, List<PSkupriceDistributorEntity> entity);


}
