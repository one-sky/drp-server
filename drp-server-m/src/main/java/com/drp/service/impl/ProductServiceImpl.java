package com.drp.service.impl;

import com.drp.Util.InitPage;
import com.drp.Util.PageModel;
import com.drp.entity.*;
import com.drp.repository.BrandRepository;
import com.drp.repository.DistributorRepository;
import com.drp.repository.ProductRepository;
import com.drp.service.ProductService;
import com.drp.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected BrandRepository brandRepository;

    @Autowired
    protected DistributorRepository distributorRepository;


    public Map<String, Object> getProductList(SpuSearchVO vo) {

        Map<String,Object> map=new HashMap<String,Object>();
        //默认情况下第一页，每页20条数据
        if(null==vo.getPage()|| 0==vo.getPage()){
            vo.setPage(1);
        }
        if(null==vo.getPageSize()|| 0==vo.getPageSize()){
            vo.setPageSize(20);
        }

        vo.setStartIndex( (vo.getPage()-1)*vo.getPageSize());
        List<SpuSearchVO> dataList = productRepository.getProductList(vo);
        PageModel pageInfo = new PageModel<SpuSearchVO>(dataList, vo.getPage(), vo.getPageSize());
        map.put("dataList",dataList);
        map.put("pageInfo",pageInfo);
        return map;

    }

    public SpuDetailVO getSpuDetail(Integer spuId, Integer distributorId) {

        SpuDetailVO spuDetailVO = new SpuDetailVO();
        SkuDetailVO skuDetailVO = new SkuDetailVO();

        // 获取spu基本信息
        SpuSearchVO vo = new SpuSearchVO();
        List<Integer> productIds = new ArrayList<Integer>();
        productIds.add(spuId);
        vo.setProductIds(productIds);
        vo.setPageSize(1);
        vo.setStartIndex(0);
        spuDetailVO.setSpuSearchVO(productRepository.getProductList(vo).get(0));

        // 记录skuDetailVO组装成的sku列表
        List<SkuDetailVO> tmp = new ArrayList<SkuDetailVO>();

        // 促销sku价格列表
        List<SkuPriceDetailVO> promoteList =productRepository.getPriceListPromotion(spuId, null);

        // 根据分销商等级获取sku价格列表、特定分销商特殊sku价格列表
        List<SkuPriceDetailVO> priceList=null;
        List<SkuPriceDetailVO> specialPriceList=null;
        priceList = productRepository.getPriceList(distributorId, spuId, null);
        if(null!=distributorId && 0 != distributorId) {
            specialPriceList = productRepository.getPriceListByDistributorId(distributorId, spuId, null);
        }

        List<SkuPriceDetailVO> resultPriceList = new ArrayList<SkuPriceDetailVO>();
        if(!priceList.isEmpty()) {
            Integer skuId = null;
            for(SkuPriceDetailVO price : priceList) {
                Integer id = price.getSkuId();
                if(!id.equals(skuId)){
                    skuDetailVO = new SkuDetailVO();
                    if(null!=resultPriceList && !resultPriceList.isEmpty()) {
                        skuDetailVO.setPriceList(resultPriceList);
                        tmp.add(skuDetailVO);
                        resultPriceList = new ArrayList<SkuPriceDetailVO>();
                    }
                    // 设置特殊分销商价格
                    if(null!=specialPriceList && !specialPriceList.isEmpty()) {
                        for(SkuPriceDetailVO specialPrice : specialPriceList) {
                            if(specialPrice.getSkuId().equals(id)){
                                resultPriceList.add(specialPrice);
                            }
                        }
                    }

                    // 设置促销价格
                    if(null!=promoteList && !promoteList.isEmpty()) {
                        for(SkuPriceDetailVO promote : promoteList) {
                            if (promote.getSkuId().equals(id)) {
                                resultPriceList.add(promote);
                            }
                        }
                    }

                }
                skuId = id;
                resultPriceList.add(price);
            }
            skuDetailVO = new SkuDetailVO();
            skuDetailVO.setPriceList(resultPriceList);
            // 设置属性信息
            tmp.add(skuDetailVO);
            spuDetailVO.setSkuDetailList(tmp);

        }
        //取到文章
        List<PProductArticleEntity> productArticleList=productRepository.getArticleList(spuId, distributorId);
        spuDetailVO.setpProductArticleEntity(productArticleList);


        return spuDetailVO;
    }

    public Map<String, Object> getCollectionProductList(Integer distributorId, Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        List<CollectProductVO> dataList = productRepository.getCollectionProductList(distributorId, initPageSize, initStartIndex);
        PageModel pageInfo = new PageModel<CollectProductVO>(dataList, initPageNum, initPageSize);
        map.put("dataList",dataList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public Integer cancelCollectionProduct(Integer distributorId, List<Integer> ids) {
        if(ids.isEmpty()) {
            return 0;
        }
        return productRepository.cancelCollectionProduct(distributorId, ids);
    }

    public Integer addCollectionProduct(Integer distributorId, List<Integer> spuIds) {
        // 判断是否存在该分销商
        if(null!=distributorRepository.getDistributorDetail(distributorId)) {
            Timestamp tmp = new Timestamp(new Date().getTime());
            DCollectionEntity entity = new DCollectionEntity();
            List<CollectProductVO> result =productRepository.getCollectionProductList(distributorId, 10000, 0);
            if(!result.isEmpty()) {
                // 判断分销商是否已经收藏了
                for(CollectProductVO collect : result) {
                    int i= 0;
                    while(i< spuIds.size()) {
                        if (collect.getSpuId() == spuIds.get(i)) {
                            break;
                        }
                        i++;
                    }
                    if(i < spuIds.size()) {
                        spuIds.remove(i);
                    }
                }
            }

            if(spuIds.isEmpty()){
                return -1;
            }
            entity.setSortBy(1);
            entity.setCreateBy(999);
            entity.setCreateTime(tmp);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(tmp);
            entity.setDistributorId(distributorId);
            return productRepository.addCollectionProduct(distributorId, spuIds, entity);
        }else {
            return -1;
        }

    }

    public List<ShoppingCartItemVO> getShoppingCartList(Integer distributorId, Integer type) {
        List<ShoppingCartItemVO> basicCartVO = productRepository.getBasicShoppingCartList(distributorId, type);
        List<SkuPriceDetailVO> priceDetailVO;
        for(ShoppingCartItemVO vo:basicCartVO) {
            priceDetailVO = new ArrayList<SkuPriceDetailVO>();

            // 促销sku价格列表
            List<SkuPriceDetailVO> promoteList =productRepository.getPriceListPromotion(null, vo.getSkuId());

            // 根据分销商等级获取sku价格列表、特定分销商特殊sku价格列表
            List<SkuPriceDetailVO> priceList=null;
            List<SkuPriceDetailVO> specialPriceList=null;
            if(null!=distributorId) {
                priceList = productRepository.getPriceList(distributorId, null, vo.getSkuId());
                specialPriceList = productRepository.getPriceListByDistributorId(distributorId, null, vo.getSkuId());
            }
            if(specialPriceList != null && !specialPriceList.isEmpty()) {
                for(SkuPriceDetailVO price:specialPriceList) {
                    priceDetailVO.add(price);
                }
            }
            if(promoteList != null && !promoteList.isEmpty()) {
                for (SkuPriceDetailVO price : promoteList) {
                    priceDetailVO.add(price);
                }
            }
            for(SkuPriceDetailVO price:priceList) {
                priceDetailVO.add(price);
            }
            vo.setSkuPriceDetailVO(priceDetailVO);
        }
        return basicCartVO;
    }

    public Integer updateShoppingCartList(List<ShoppingCartItemVO> shoppingCartItemVOList, Integer distributorId) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        PCartEntity cart;
        List<ShoppingCartItemVO> dbCartList = this.getShoppingCartList(distributorId, null);
        for (ShoppingCartItemVO vo : shoppingCartItemVOList) {
            cart = new PCartEntity();
            cart.setSkuId(vo.getSkuId());
            cart.setDistributorId(distributorId);

            Integer quantity = vo.getQuantity();

            // 数量为正的时候加入购物车
            if (quantity > 0) {
                if (null != dbCartList && !dbCartList.isEmpty()) {
                    for (ShoppingCartItemVO dbCart : dbCartList) {
                        // 购物车中相同type已经有这一个sku商品 update
                        if (dbCart.getSkuId().equals(vo.getSkuId()) && dbCart.getType().equals(vo.getType())) {
                            cart.setId(dbCart.getId());
                            cart.setLastUpdateBy(999);
                            cart.setLastUpdateTime(currentTime);
                            cart.setQuantity(dbCart.getQuantity() + quantity);
                            String status = brandRepository.isAgentBrand(distributorId, cart.getSkuId());
                            cart.setType("Y".equals(status) ? 1 :2);
                            productRepository.updateShoppingCart(cart);
                            break;
                        }
                    }
                }
                // insert
                if (null == dbCartList || dbCartList.isEmpty() || 0 == (cart.getQuantity())) {
                    cart.setLastUpdateBy(999);
                    cart.setLastUpdateTime(currentTime);
                    cart.setCreateBy(999);
                    cart.setCreateTime(currentTime);
                    cart.setQuantity(quantity);
                    String status = brandRepository.isAgentBrand(distributorId, cart.getSkuId());
                    cart.setType(status.equals("Y") ? 1 :2);
                    productRepository.insertShoppingCart(cart);
                    break;
                }
            } else {
                quantity = -quantity;
                if (null != dbCartList && !dbCartList.isEmpty()) {
                    for (ShoppingCartItemVO dbCart : dbCartList) {
                        // 购物车中相同type已经有这一个sku商品但数量少于修改的，delete

                        if (dbCart.getSkuId().equals(vo.getSkuId()) && dbCart.getType().equals(vo.getType())) {
                            if (dbCart.getQuantity() <= quantity) {
                                cart.setId(dbCart.getId());
                                productRepository.deleteShoppingCart(cart);
                                break;
                            } else {
                                cart.setId(dbCart.getId());
                                cart.setLastUpdateBy(999);
                                cart.setLastUpdateTime(currentTime);
                                cart.setQuantity(dbCart.getQuantity() - quantity);
                                String status = brandRepository.isAgentBrand(distributorId, cart.getSkuId());
                                cart.setType("Y".equals(status) ? 1 :2);
                                productRepository.updateShoppingCart(cart);
                            }
                            break;

                        }
                    }
                    return 0;

                }
            }
        }
        return 0;
    }

    public Integer updateSkuLockStock(List skuList) {
        return productRepository.updateSkuLockStock(skuList);
    }

}
