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
        // 记录skuDetailVO组装成的sku列表
        List<SkuDetailVO> tmp = new ArrayList<SkuDetailVO>();

        // 促销sku价格列表
        List<SkuPriceDetailVO> promoteList =productRepository.getPriceListPromotion(spuId, null);

        // 获取该spu的所有skuId
        List<Integer> skuIdList=new ArrayList<Integer>();
        for(SkuPriceDetailVO sku:promoteList){
            skuIdList.add(sku.getSkuId());
        }

        // 根据分销商等级获取sku价格列表、特定分销商特殊sku价格列表
        List<SkuPriceDetailVO> priceList=null;
        List<SkuPriceDetailVO> specialPriceList=null;
        if(null!=distributorId) {
            priceList = productRepository.getPriceList(skuIdList, distributorId);
            specialPriceList = productRepository.getPriceListByDistributorId(skuIdList, distributorId);
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
                    for(SkuPriceDetailVO promote : promoteList) {
                        if(promote.getSkuId().equals(id)){
                            resultPriceList.add(promote);
                        }
                    }
                    for(SkuPriceDetailVO specialPrice : specialPriceList) {
                        if(specialPrice.getSkuId().equals(id)){
                            resultPriceList.add(specialPrice);
                        }
                    }
                }
                skuId = id;
                resultPriceList.add(price);
            }
            skuDetailVO = new SkuDetailVO();
            skuDetailVO.setPriceList(resultPriceList);
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
        return productRepository.cancelCollectionProduct(distributorId, ids);
    }

    public Integer addCollectionProduct(Integer distributorId, List<Integer> spuIds) {
        // 判断是否存在该分销商
        if(null!=distributorRepository.getDistributorDetail(distributorId)) {
            Timestamp tmp = new Timestamp(new Date().getTime());
            DCollectionEntity entity = new DCollectionEntity();
            List<CollectProductVO> result =productRepository.getCollectionProductList(distributorId, 10000, 0);
            entity.setSortBy(result.get(result.size()-1).getSortBy()+1);
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
            List<SkuPriceDetailVO> promoteList =productRepository.getPriceListPromotion(null, vo.getSpuId());

            // 获取该spu的所有skuId
            List<Integer> skuIdList=new ArrayList<Integer>();
            skuIdList.add(vo.getSkuId());

            // 根据分销商等级获取sku价格列表、特定分销商特殊sku价格列表
            List<SkuPriceDetailVO> priceList=null;
            List<SkuPriceDetailVO> specialPriceList=null;
            if(null!=distributorId) {
                priceList = productRepository.getPriceList(skuIdList, distributorId);
                specialPriceList = productRepository.getPriceListByDistributorId(skuIdList, distributorId);
            }
            for(SkuPriceDetailVO price:specialPriceList) {
                priceDetailVO.add(price);
            }
            for(SkuPriceDetailVO price:promoteList) {
                priceDetailVO.add(price);
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
                if (null == dbCartList || !dbCartList.isEmpty() || 0 == (cart.getQuantity())) {
                    cart.setLastUpdateBy(999);
                    cart.setLastUpdateTime(currentTime);
                    cart.setCreateBy(999);
                    cart.setCreateTime(currentTime);
                    cart.setQuantity(quantity);
                    String status = brandRepository.isAgentBrand(distributorId, cart.getSkuId());
                    cart.setType(status == "Y" ? 1 :2);
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
                                cart.setId(dbCart.getQuantity());
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
