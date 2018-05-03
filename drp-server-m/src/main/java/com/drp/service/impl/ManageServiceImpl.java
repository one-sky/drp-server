package com.drp.service.impl;

import com.drp.Util.BeanUtils;
import com.drp.Util.InitPage;
import com.drp.Util.PageModel;
import com.drp.entity.*;
import com.drp.repository.*;
import com.drp.service.CategoryService;
import com.drp.service.ManageService;
import com.drp.service.OrderService;
import com.drp.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected ManageRepository manageRepository;

    @Autowired
    protected DistributorRepository distributorRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected BrandRepository brandRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected ResourceRepository resourceRepository;

    public Map<String, Object> getUserList(ManageUserVO entity, Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        List<ManageUserVO> userList = manageRepository.getUserList(entity, initPageSize, initStartIndex);
        List<ManageUserVO> distributorList = new ArrayList<ManageUserVO>();
        for(ManageUserVO user:userList) {
            if(null!=user.getDistributorId()) {
                distributorList.add(user);
            }
        }
        List<ManageUserVO> vipList = manageRepository.getDistributorVip(distributorList);
        for(ManageUserVO user:userList) {
            // 已成为分销商不是用户了
            if(null!=user.getDistributorId())
            for(ManageUserVO vip:vipList) {
                if(vip.getDistributorId().equals(user.getDistributorId())) {
                    vip.setUserId(user.getUserId());
                    BeanUtils.copyProperties(vip, user);
                    break;
                }
            }
        }
        PageModel pageInfo = new PageModel<ManageUserVO>(userList, initPageNum, initPageSize);
        map.put("dataList",userList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public Integer updatePoint(Integer userId, Integer point, String reason) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        ManageUserVO entity = new ManageUserVO();
        entity.setUserId(userId);
        ManageUserVO dbEntity = manageRepository.getUserList(entity, 1, 0).get(0);
        if(null == dbEntity.getPoints()) {
            return -100;

        }
        if(dbEntity.getPoints() + point < 0) {
            return -10;
        }
        // 更新分销商信息中的积分
        dbEntity.setPoints(dbEntity.getPoints() + point);
        dbEntity.setLastUpdateTime(currentTime);
        DDistributorEntity distributorEntity = new DDistributorEntity();
        BeanUtils.copyProperties(dbEntity, distributorEntity);
        distributorEntity.setId(dbEntity.getDistributorId());
        distributorRepository.updateDistributor(distributorEntity);

        Integer distributorId = dbEntity.getDistributorId();
        DPointsHistoryEntity pointsHistoryEntity = new DPointsHistoryEntity();
        pointsHistoryEntity.setDistributorId(distributorId);
        // 系统修改积分
        pointsHistoryEntity.setPointsType("2");

        SearchVO vo = new SearchVO();
        vo.setDistributorId(distributorId);
        vo.setStartIndex(0);
        vo.setPageSize(1);
        List<DPointsHistoryEntity> dbPoint = distributorRepository.getPointList(vo);
        if(dbPoint.isEmpty()) {
            pointsHistoryEntity.setPointsBefore(0);
        } else {
            pointsHistoryEntity.setPointsBefore((dbPoint.get(0)).getPointsAfter());
        }
        pointsHistoryEntity.setPoints(point);
        pointsHistoryEntity.setPointsAfter(point + pointsHistoryEntity.getPointsBefore());
        pointsHistoryEntity.setCreateBy(999);
        pointsHistoryEntity.setCreateTime(currentTime);
        pointsHistoryEntity.setLastUpdateBy(999);
        pointsHistoryEntity.setLastUpdateTime(currentTime);
        pointsHistoryEntity.setReason(reason);
        return distributorRepository.insertPoint(pointsHistoryEntity);

    }

    public Map<String, Object> getBrandList(RBrandEntity entity, Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        List<RBrandEntity> brandList = manageRepository.getBrandList(entity, initPageSize, initStartIndex);
        PageModel pageInfo = new PageModel<RBrandEntity>(brandList, initPageNum, initPageSize);
        map.put("dataList",brandList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public Integer saveBrand(RBrandEntity entity) {
        Timestamp tmp = new Timestamp(new Date().getTime());
        Integer id = entity.getId();
        //新建时
        if (null == id || id == 0) {
            entity.setCreateBy("999");
            entity.setCreateTime(tmp);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(tmp);
            entity.setIsFinished("Y");
            return manageRepository.insertBrand(entity);
        } else {
            // 若修改了品牌所属类目，需要判断该品牌下是否有商品，否则类目不一致
            RBrandEntity dbBrand = brandRepository.getBrandById(entity.getId());
            if(dbBrand.getCategoryId() != entity.getCategoryId()) {
                SpuSearchVO vo = new SpuSearchVO();
                List<Integer> brandIds = new ArrayList<Integer>();
                List<Integer> categoryIds = new ArrayList<Integer>();
                brandIds.add(dbBrand.getId());
                categoryIds.add(dbBrand.getCategoryId());
                vo.setBrandIds(brandIds);
                vo.setCategoryIds(categoryIds);
                vo.setStartIndex(0);
                vo.setPageSize(1);
                if(!productRepository.getProductList(vo).isEmpty()) {
                    return -10;
                }
            }
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(tmp);
            return manageRepository.updateBrand(entity);
        }
    }

    public Integer deleteBrand(Integer brandId) {
        SpuSearchVO vo = new SpuSearchVO();
        List<Integer> brandIds = new ArrayList<Integer>();
        brandIds.add(brandId);
        vo.setBrandIds(brandIds);
        vo.setStartIndex(0);
        vo.setPageSize(1);
        if(!productRepository.getProductList(vo).isEmpty()) {
            return -10;
        }
        return manageRepository.deleteBrand(brandId);
    }

    public Map<String, Object> getAgentBrandList(AgentBrandVO entity, Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        List<AgentBrandVO> brandList = manageRepository.getAgentBrandList(entity, initPageSize, initStartIndex);
        PageModel pageInfo = new PageModel<AgentBrandVO>(brandList, initPageNum, initPageSize);
        map.put("dataList",brandList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public Integer updateAgentStatus(DAgentBrandEntity entity) {
        Timestamp tmp = new Timestamp(new Date().getTime());
        DAgentBrandEntity agent = manageRepository.getAgentBrandByKey(entity.getId());
        if(null == agent) {
            return -1;
        }
        if(entity.getStatus().equals("R")) {
            agent.setReason(entity.getReason());
        } else {
            agent.setReason(null);
        }

        agent.setStatus(entity.getStatus());
        agent.setLastUpdateTime(tmp);
        return manageRepository.updateAgentStatus(agent);

    }

    public Integer saveCategory(PCategoryEntity entity) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        Integer id= entity.getId();
        //新建时
        if(null == id || id ==0){
            // 添加二级类目
            if(entity.getLevel().equals("2")) {
                entity.setIsLeaf(1);
            } else { // 添加一级类目
                entity.setIsLeaf(0);
                entity.setParentId(0);

            }
            entity.setCreateBy(999);
            entity.setCreateTime(currentTime);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(currentTime);
            return categoryRepository.insertCategory(entity);

        }else{
            // 修改
            PCategoryEntity dbCategory=categoryRepository.getCategoryById(id);
            // 若修改了类目的所属父类目, 须二级类目是否有相关商品或相关属性; 关闭类目使用,  二级类目其商品是否有使用中，或属性有使用中
            if(entity.getLevel().equals("2")) {
                if (dbCategory.getParentId() != entity.getParentId() || (dbCategory.getStatus() == 1 && entity.getStatus() == 2)) {
                    List<Integer> categoryIds = new ArrayList<Integer>();
                    categoryIds.add(id);
                    SpuSearchVO search = new SpuSearchVO();
                    search.setCategoryIds(categoryIds);
                    search.setStartIndex(0);
                    search.setPageSize(10000);
                    List<SpuSearchVO> productList = productRepository.getProductList(search);
                    if (!productList.isEmpty()) {
                        return -10;
                    }
                    for (SpuSearchVO product : productList) {
                        if (product.getOnline().equals("Y")) {
                            return -20;
                        }
                    }
                    List<PCategoryAttrEntity> attrList = categoryRepository.getAttributeList(1, categoryIds);
                    if (!attrList.isEmpty()) {
                        return -11;
                    }
                    for (PCategoryAttrEntity attr : attrList) {
                        if (attr.getStatus() == 1) {
                            return -21;
                        }
                    }

                }
            } else {
                // 关闭类目使用, 需要判断一级类目的下属类目是否都是关闭状态
                if(dbCategory.getStatus() == 1 && entity.getStatus() == 2) {
                    List<PCategoryEntity> categoryList = categoryRepository.getCategoryByParentId(entity.getId());
                    for(PCategoryEntity category:categoryList) {
                        if(category.getStatus() == 1) {
                            return -30;
                        }
                    }
                }
            }

            BeanUtils.copyProperties(entity,dbCategory);
            dbCategory.setLastUpdateBy(999);
            dbCategory.setLastUpdateTime(currentTime);
            return categoryRepository.updateCategory(dbCategory);

        }
    }

    public Integer deleteCategory(PCategoryEntity entity) {
        // 一级类目判断是否存在二级类目， 二级类目判断是否存在商品或属性
        Integer id= entity.getId();
        if(entity.getLevel().equals("2")) {
            List<Integer> categoryIds = new ArrayList<Integer>();
            categoryIds.add(id);
            SpuSearchVO search = new SpuSearchVO();
            search.setCategoryIds(categoryIds);
            search.setStartIndex(0);
            search.setPageSize(10000);
            List<SpuSearchVO> productList = productRepository.getProductList(search);
            if (!productList.isEmpty()) {
                return -10;
            }
            List<PCategoryAttrEntity> attrList = categoryRepository.getAttributeList(1, categoryIds);
            if (!attrList.isEmpty()) {
                return -11;
            }
        }
        return categoryRepository.deleteCategory(entity);
    }

    public Integer saveAttr(PCategoryAttrEntity entity) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        Integer id= entity.getId();
        //新建时
        if(null == id || id ==0){
            entity.setCreateBy(999);
            entity.setCreateTime(currentTime);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(currentTime);
            return categoryRepository.insertAttr(entity);
        }else{
            // 修改
            PCategoryAttrEntity dbAttr=categoryRepository.getAttrById(entity.getId());
            // 若修改了属性的所属类目, 是否有相关商品
            if (dbAttr.getCategoryId() != entity.getCategoryId()) {
                List<Integer> attrIds = new ArrayList<Integer>();
                attrIds.add(entity.getId());
                List<PAttrValueEntity> attrValueList = categoryRepository.getAttrValueList(-1, attrIds);
                if (!attrValueList.isEmpty()) {
                    return -10;
                }
                for (PAttrValueEntity attrValue : attrValueList) {
                    if (null == attrValue.getSpuIds() || attrValue.getSpuIds().equals("")) {
                        return -20;
                    }
                }
            }

            BeanUtils.copyProperties(entity,dbAttr);
            dbAttr.setLastUpdateBy(999);
            dbAttr.setLastUpdateTime(currentTime);
            return categoryRepository.updateAttr(dbAttr);
        }
    }

    public Integer deleteAttr(PCategoryAttrEntity entity) {
        // 是否存在子属性
        Integer id= entity.getId();
        List<Integer> attrIds = new ArrayList<Integer>();
        attrIds.add(id);
//        List<PAttrValueEntity> attrValueList = categoryRepository.getAttrValueList(-1, attrIds);
//        if (!attrValueList.isEmpty()) {
//            return -10;
//        }
        return categoryRepository.deleteAttr(entity);
    }

    public Integer saveAttrValue(PAttrValueEntity entity) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        Integer id= entity.getId();
        //新建时
        if(null == id || id ==0){
            entity.setCreateBy(999);
            entity.setCreateTime(currentTime);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(currentTime);

            return categoryRepository.insertAttrValue(entity);
        }else{
            // 修改
            PAttrValueEntity dbAttrValue=categoryRepository.getAttrValueById(entity.getId());
            // 若修改了属性的所属属性大类, 是否有相关商品
            if (dbAttrValue.getAttrId() != entity.getAttrId()) {
                if (null == dbAttrValue.getSpuIds() || dbAttrValue.getSpuIds().equals("")) {
                    return -10;
                }
            }

            BeanUtils.copyProperties(entity,dbAttrValue);
            dbAttrValue.setLastUpdateBy(999);
            dbAttrValue.setLastUpdateTime(currentTime);
            return categoryRepository.updateAttrValue(dbAttrValue);
        }
    }

    public Integer deleteAttrValue(PAttrValueEntity entity) {
        // 是否存在商品
        Integer id= entity.getId();
        List<Integer> attrIds = new ArrayList<Integer>();
        attrIds.add(id);
//        PAttrValueEntity dbAttrValue=categoryRepository.getAttrValueById(entity.getId());
//        if (null == dbAttrValue.getSpuIds() || dbAttrValue.getSpuIds().equals("")) {
//            return -10;
//        }
        return categoryRepository.deleteAttrValue(entity);
    }

    public Integer saveProduct(PProductCategoryEntity entity) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        Integer id= entity.getId();
        List<Integer> tmpBeforeIds = new ArrayList<Integer>();
        List<Integer> tmpAfterIds = new ArrayList<Integer>();
        if(null == id || id ==0){
            entity.setCreateBy(999);
            entity.setCreateTime(currentTime);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(currentTime);
            id = productRepository.insertProduct(entity);
            entity.setId(id);
        }
        if (null!=entity.getSpuAttr() && !entity.getSpuAttr().equals("")) {
            // 属性大类ids, 修改或新增后
            if(entity.getSpuAttr().split(";")[1].length() > 0) {
                for (String a : entity.getSpuAttr().split(";")[1].split(",")) {
                    tmpAfterIds.add(Integer.parseInt(a));
                }
            }
            // 属性大类ids, 修改或新增前
            if(entity.getSpuAttr().split(";")[0].length() > 0) {
                for (String a : entity.getSpuAttr().split(";")[0].split(",")) {
                    tmpBeforeIds.add(Integer.parseInt(a));
                }
            }


//            if(tmpNames.length > 0) {
//                for (String attrName : tmpNames) {
//                    attrNames.add(attrName);
//                }
//                List<Integer> categoryIds = new ArrayList<Integer>();
//                categoryIds.add(entity.getCategoryId());
//                List<AttrVO> attrList = categoryService.getAttributeList(1, categoryIds);
//                for (String attrName : attrNames) {
//                    for (AttrVO dbAttr : attrList) {
//                        if (dbAttr.getAttrName().equals(attrName)) {
//                            spuAttr = spuAttr + attrName + "：";
//                            List<PAttrValueEntity> attrValueList = dbAttr.getAttrValueEntityList();
//                            for (PAttrValueEntity attrValue : attrValueList) {
//                                spuAttr = spuAttr + attrValue.getAttrValue() + "、";
//                            }
//                            spuAttr = spuAttr.substring(0, spuAttr.length() - 1) + "；";
//                            break;
//                        }
//                    }
//                }
//                spuAttr = spuAttr.substring(0, spuAttr.length() - 1);
//            }

            entity.setSpuAttr(entity.getSpuAttr().split(";")[1]);

            List<PAttrValueEntity> attrList1 = new ArrayList<PAttrValueEntity>();
            // 删除原商品所有的属性标签
            if(!tmpBeforeIds.isEmpty()) {
                attrList1 = categoryRepository.getAttrValueByIdList(tmpBeforeIds);
                for (PAttrValueEntity attr : attrList1) {
                    String[] spuIds = attr.getSpuIds().split(",");
                    String tmp = null;
                    for (int i = 0; i < spuIds.length; i++) {
                        if (!spuIds[i].equals(id + "")) {
                            tmp = spuIds[i] + ",";
                        }
                    }
                    if(tmp!=null) {
                        tmp = tmp.substring(0, tmp.length() - 1);
                    }
                    attr.setSpuIds(tmp);

                }
            }
            if(!attrList1.isEmpty() && categoryRepository.updateAttrValueList(attrList1) < 0) {
                return -1;
            }
            attrList1.clear();
            if(!tmpAfterIds.isEmpty()) {
                List<PAttrValueEntity> attrList2 = categoryRepository.getAttrValueByIdList( tmpAfterIds);
                for (PAttrValueEntity attr : attrList2) {
                    String spuIds = null;
                    if (attr.getSpuIds()!=null && !attr.getSpuIds().equals("")) {
                        spuIds = attr.getSpuIds() + "," + id;
                    } else {
                        spuIds = id + "";
                    }
                    attr.setSpuIds(spuIds);
                    attrList1.add(attr);
                }
            }

            if(!attrList1.isEmpty() && categoryRepository.updateAttrValueList(attrList1) < 0) {
                return -1;
            }
        }

        //修改

        PProductCategoryEntity dbEntity = productRepository.getProductById(entity.getId());
        BeanUtils.copyProperties(entity,dbEntity);
        dbEntity.setLastUpdateBy(999);
        dbEntity.setLastUpdateTime(currentTime);
        return productRepository.updateProduct(dbEntity);
    }

    public Integer saveSku(PProductSkuEntity entity, List<PProductQuantityEntity> priceList) {
        //新建时
        Integer id= entity.getId();
        if(null == id || id ==0){
            Integer dbId = productRepository.insertSku(entity);
            if( dbId > 0) {
                return productRepository.insertPriceList(dbId, priceList);
            };
            return 0;
        }else{
            PProductSkuEntity dbEntity = productRepository.getSkuById(id);
            BeanUtils.copyProperties(entity,dbEntity);
            return productRepository.updateSku(dbEntity);
        }
    }

    public Integer savePriceList(Integer skuId, List<PProductQuantityEntity> entity) {
        if(productRepository.deletePriceList(skuId)>0) {
            return productRepository.insertPriceList(skuId, entity);
        }
        return 0;
    }

    public Integer updateOrder(OOrderEntity entity) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        OOrderEntity dbOrder = orderRepository.selectOrderByKey(entity.getId());
        dbOrder.setStatus(30);
        dbOrder.setDeliveryName(entity.getDeliveryName());
        dbOrder.setDeliveryCode(entity.getDeliveryCode());
        dbOrder.setDeliveryTime(currentTime);
        dbOrder.setLastUpdateTime(currentTime);
        return orderRepository.updateOrder(dbOrder);
    }

    public Integer savePromotion(PProductPromotionEntity entity) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        Integer id = entity.getId();
        //新建时
        if (null == id || id == 0) {
            entity.setCreateBy(999);
            entity.setCreateTime(currentTime);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(currentTime);
            return resourceRepository.insertPromotion(entity);
        } else {
            PProductPromotionEntity dbPromotion = resourceRepository.getPromotionById(entity.getId());
            BeanUtils.copyProperties(entity, dbPromotion);
            dbPromotion.setLastUpdateBy(999);
            dbPromotion.setLastUpdateTime(currentTime);
            return resourceRepository.updatePromotion(entity);
        }

    }

    public Integer savePromotionPriceList(Integer promotionId, Integer skuId, List<PPromoteProductEntity> entity) {
        boolean result = true;
        if(!resourceRepository.selectPromotionPriceList(promotionId, skuId).isEmpty()) {
            result = resourceRepository.deletePromotionPriceList(promotionId, skuId) > 0;
        }
        if(result) {
            return resourceRepository.insertPromotionPriceList(promotionId, skuId, entity);
        }
        return 0;
    }

    public Integer saveSpecialPriceList(Integer distributorId, Integer skuId, List<PSkupriceDistributorEntity> entity) {
        boolean result = true;
        if(!productRepository.selectSpecialPriceList(distributorId, skuId).isEmpty()) {
            result = productRepository.deleteSpecialPriceList(distributorId, skuId) > 0;
        }
        if(result) {
            return productRepository.insertSpecialPriceList(distributorId, skuId, entity);
        }
        return 0;
    }
}

