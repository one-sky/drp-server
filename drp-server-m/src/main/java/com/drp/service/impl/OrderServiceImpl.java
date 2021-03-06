package com.drp.service.impl;

import com.drp.Util.InitPage;
import com.drp.Util.PageModel;
import com.drp.entity.*;
import com.drp.repository.DistributorRepository;
import com.drp.repository.OrderRepository;
import com.drp.repository.ProductRepository;
import com.drp.repository.ResourceRepository;
import com.drp.service.OrderService;
import com.drp.service.ProductService;
import com.drp.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected  ProductService productService;

    @Autowired
    protected DistributorRepository distributorRepository;

    @Autowired
    protected ResourceRepository resourceRepository;


    /**
     * 生成订单方法
     * @param type 1、批发；2、一件代发
     * @param addressVO
     * @param shoppingCartItemVOList
     * @param distributorId
     * @param buyerMessage
     * @return
     */
    public String generateOrder(Integer type, DAddressEntity addressVO, List<ShoppingCartItemVO> shoppingCartItemVOList,
                                 Integer distributorId, String buyerMessage) {

        Timestamp currentTime = new Timestamp(new Date().getTime());
        String error = "";

        OOrderEntity orderVO = new OOrderEntity();
        OOrderItemEntity orderItemVO;
        List<OOrderItemEntity> orderItemVOList = new ArrayList<OOrderItemEntity>();

        List skuList = new ArrayList();
        PProductSkuEntity skuEntity = new PProductSkuEntity();
        PPromoteProductEntity promotionEntity = new PPromoteProductEntity();
        PSkupriceDistributorEntity pSkupriceDistributorEntity  = new PSkupriceDistributorEntity();


        //获取商品判断是否都上架
        SpuSearchVO searchVO = new SpuSearchVO();
        List<Integer> spuIds = new ArrayList<Integer>();
        for(ShoppingCartItemVO vo:shoppingCartItemVOList){

            spuIds.add(vo.getSpuId());
        }
        searchVO.setProductIds(spuIds);
        searchVO.setStartIndex(0);
        searchVO.setPageSize(10000);
        List<SpuSearchVO> result = productRepository.getProductList(searchVO);
        try {
            for(SpuSearchVO vo:result){
                if("N".equals(vo.getOnline())){
                    return "有未上架的商品："+ vo.getProductId();
                }
            }
        }catch(Exception e){
            return "查询商品上架信息出错！";
        }

        // 获取价格
        List<Integer> skuIdList=new ArrayList<Integer>();
        for(ShoppingCartItemVO vo:shoppingCartItemVOList){
            skuIdList.add(vo.getSkuId());
        }

        List<List<SkuPriceDetailVO>> priceList= productRepository.searchPrice(skuIdList, distributorId);

        BigDecimal totalAmount = new BigDecimal(0);

        BigDecimal paidAmount = new BigDecimal(0);

        Integer orderItemQuantity = 0;

        for(ShoppingCartItemVO vo:shoppingCartItemVOList){

            // 原价
            List<SkuPriceDetailVO> originalPrice = priceList.get(0);

            // 特殊价格
            List<SkuPriceDetailVO> specialPrice = priceList.get(1);

            // 促销价格
            List<SkuPriceDetailVO> promotePrice = priceList.get(2);

            // 普通价格
            List<SkuPriceDetailVO> commonPrice = priceList.get(3);

            Integer skuId=vo.getSkuId();
            Integer quantity = vo.getQuantity();

            orderItemQuantity += quantity;

            orderItemVO = new OOrderItemEntity();
            orderItemVO.setSkuId(skuId);
            orderItemVO.setSkuQuantity(quantity);
            orderItemVO.setStatus("10"); // 已提交
            orderItemVO.setCreateBy(999);
            orderItemVO.setCreateTime(currentTime);
            orderItemVO.setLastUpdateBy(999);
            orderItemVO.setLastUpdateTime(currentTime);
            vo.setQuantity(-vo.getQuantity());


            //设置原价 会员等级最低的，起批价1件的无促销的时候的价格
            for(SkuPriceDetailVO price: originalPrice) {
                if (skuId.equals(price.getSkuId())) {
                    totalAmount = totalAmount.add(price.getPrice().multiply(new BigDecimal(quantity)));
                    orderItemVO.setOrginalPrice(price.getPrice());
                    break;
                }
            }


            // 批发
            if( type.equals(1)) {

                //设置特殊价格
                for(SkuPriceDetailVO special: specialPrice) {
                    if (skuId.equals(special.getSkuId()) && quantity <=special.getEndPiece()
                            && quantity >=special.getStartPiece()) {
                        if(special.getStock()>=quantity) {
                            paidAmount = paidAmount.add(special.getPrice().multiply(new BigDecimal(quantity)));
                            orderItemVO.setSkuPrice(special.getPrice());

                            // 设置库存减少
                            pSkupriceDistributorEntity.setSkuId(skuId);
                            pSkupriceDistributorEntity.setDistributorId(distributorId);
                            pSkupriceDistributorEntity.setChangeStock(-1*quantity);
                            skuList.add(pSkupriceDistributorEntity);
                            break;
                        }
                    }
                }

                // 未设特殊价格时设置促销价格
                if(null == orderItemVO.getSkuPrice() || orderItemVO.getSkuPrice().equals(0)) {

                    for(SkuPriceDetailVO promote: promotePrice) {
                        if (skuId.equals(promote.getSkuId()) && quantity <=promote.getEndPiece()
                                && quantity >=promote.getStartPiece()) {
                            if(promote.getStock()>=vo.getQuantity()) {
                                paidAmount = paidAmount.add(promote.getPrice().multiply(new BigDecimal(quantity)));
                                orderItemVO.setSkuPrice(promote.getPrice());

                                // 设置库存减少
                                promotionEntity.setSkuId(skuId);
                                promotionEntity.setPromotionId(promote.getPromotionId());
                                promotionEntity.setLowQuantity(promote.getStartPiece());
                                promotionEntity.setChangeStock(-1*quantity);
                                skuList.add(promotionEntity);

                            } else {
                                error += skuId +"的促销库存不足;";
                            }
                            break;
                        }
                    }
                    // 不满足特殊价格促销价格时设置普通价格
                    if (null == orderItemVO.getSkuPrice() || orderItemVO.getSkuPrice().equals(0)) {
                        for (SkuPriceDetailVO common : commonPrice) {
                            if (skuId.equals(common.getSkuId()) && quantity <= common.getEndPiece()
                                    && quantity >= common.getStartPiece()) {
                                if (common.getStock() >= vo.getQuantity()) {
                                    // 设置库存减少
                                    skuEntity.setId(skuId);
                                    skuEntity.setChangeStock(-1*quantity);
                                    skuList.add(skuEntity);
                                    paidAmount = paidAmount.add(common.getPrice().multiply(new BigDecimal(quantity)));
                                    orderItemVO.setSkuPrice(common.getPrice());

                                } else {
                                    error += skuId +"的库存不足;";
                                }
                                break;
                            }
                        }
                    }

                }
            } else {
                orderItemVO.setSkuPrice(orderItemVO.getOrginalPrice());
            }
            orderItemVOList.add(orderItemVO);
        }

        if(null!=error && !error.equals("")){
            return error;
        }

        orderVO.setType(type);
        orderVO.setStatus(10);
        orderVO.setItemSubtoal(totalAmount);
        orderVO.setPaidAmount(paidAmount);
        orderVO.setOrderItemQuantity(orderItemQuantity);
        orderVO.setShippingCost(BigDecimal.valueOf(0));
        orderVO.setTotalAmount(totalAmount.add(orderVO.getShippingCost()));
        orderVO.setOrderTime(currentTime); // 下单时间
        if(null!=buyerMessage && !buyerMessage.equals("")){
            orderVO.setBuyerMessage(buyerMessage);
        }
        orderVO.setArea(addressVO.getAreaDesc());
        orderVO.setCity(addressVO.getCityDesc());
        orderVO.setProvince(addressVO.getProvinceDesc());
        orderVO.setDetailAddress(addressVO.getDetailAddress());
        orderVO.setReceiverPhone(addressVO.getPhone());
        orderVO.setReceiverName(addressVO.getReceiverName());

        orderVO.setDistributorId(distributorId);
        orderVO.setPaymentStatus(0);
        //生成订单号
        int  machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }

        Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(date);
        // 0 代表前面补充0
        // 6 代表长度为6
        // d 代表参数为正数型
        orderVO.setOrderCode(dateNowStr + machineId + String.format("%06d", hashCodeV));


        int data;
        // 普通价格的时候修改sku的库存，以锁定sku
        data = productService.updateSkuLockStock(skuList);
        if(data<0) {
            return "锁定失败";
        }

        //减扣购物车
        data = productService.updateShoppingCartList(shoppingCartItemVOList,distributorId);
        if(data<0) {
            error += "生成订单失败";
            return error;
        }
        //插入订单记录, data为orderId
        data = orderRepository.insertOrder(orderVO);

        // 插入订单明细
        orderRepository.insertOrderItemList(orderItemVOList, data);
        return data + "";

    }

    public Integer payOrder( OOrderEntity orderVO) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(orderVO.getId());
        List<OOrderEntity> dbVOList = this.searchOrderById(list);
        if(!dbVOList.isEmpty()) {
            OOrderEntity dbVO = dbVOList.get(0);
            if(Integer.valueOf(10).equals(dbVO.getStatus())) {
                Timestamp currentTime = new Timestamp(new Date().getTime());
                dbVO.setStatus(20);
                dbVO.setPaymentStatus(2);
                dbVO.setPaymentTime(currentTime);
                dbVO.setPaymentChannel(orderVO.getPaymentChannel());
                dbVO.setLastUpdateBy(999);
                dbVO.setLastUpdateTime(currentTime);
                // 支付成功，修改用户积分
                if(orderRepository.payOrder(dbVO) > 0) {
                    Integer distributorId = orderVO.getDistributorId();
                    DPointsHistoryEntity entity = new DPointsHistoryEntity();
                    entity.setDistributorId(distributorId);
                    // 交易成功赠送积分
                    entity.setPointsType("1");
                    DPointsEntity pointRule = resourceRepository.getPointRule();
                    SearchVO vo = new SearchVO();
                    vo.setDistributorId(distributorId);
                    vo.setStartIndex(0);
                    vo.setPageSize(1);
                    List<DPointsHistoryEntity> dbEntity = distributorRepository.getPointList(vo);
                    if(dbEntity.isEmpty()) {
                        entity.setPointsBefore(0);
                    } else {
                        entity.setPointsBefore(((DPointsHistoryEntity)dbEntity.get(0)).getPointsAfter());
                    }
                    Integer point = orderVO.getTotalAmount().multiply(new BigDecimal(pointRule.getPointsAmount()))
                                                            .divide(new BigDecimal(pointRule.getActionAmount())).intValue();
                    entity.setPoints(point);
                    entity.setPointsAfter(point + entity.getPointsBefore());
                    entity.setCreateBy(999);
                    entity.setCreateTime(currentTime);
                    entity.setLastUpdateBy(999);
                    entity.setLastUpdateTime(currentTime);
                    entity.setOrderId(orderVO.getId());
                    entity.setOrderNumber(orderVO.getOrderCode());
                    entity.setOrderTime(orderVO.getOrderTime());
                    if(distributorRepository.insertPoint(entity) > 0 ) {
                        // 修改分销商个人信息的point与totalAmount
                        DDistributorEntity distributorEntity = distributorRepository.getDistributorDetail(distributorId);
                        distributorEntity.setPoints(distributorEntity.getPoints() + point);
                        distributorEntity.setTotalAmount(distributorEntity.getTotalAmount().add(orderVO.getTotalAmount()));
                        distributorEntity.setLastUpdateTime(currentTime);
                        return distributorRepository.updateDistributor(distributorEntity);

                    }


                }

            }
        }


        return -1;

    }

    public List<OOrderEntity> searchOrderById(List<Integer> ids) {
        return orderRepository.searchOrderById(ids);
    }

    public String cancelOrderList(List<OOrderEntity> orderList) {
        Timestamp tmp = new Timestamp(new Date().getTime());
        String error ="";
        List<Integer> orderIds = new ArrayList<Integer>();
        for(OOrderEntity entity:orderList) {
            if(Integer.valueOf(0).equals(entity.getId())) {
                error = "缺id";
                return error;
            }
            orderIds.add(entity.getId());
        }

        // 查找该订单// 查找该订单
        List<OOrderEntity> dbEntityList= this.searchOrderById(orderIds);
        for(OOrderEntity entity:dbEntityList) {
            for(OOrderEntity order:orderList) {
                if(order.getId()==entity.getId()) {
                    entity.setStatus(0);
                    entity.setCancelType(order.getCancelType());
                    entity.setCancelReason(order.getCancelReason());
                    entity.setCancelTime(tmp);
                    entity.setLastUpdateTime(tmp);
                    entity.setLastUpdateBy(999);
                    break;
                }
            }
        }
        return orderRepository.cancelOrderList(dbEntityList);
    }

    public  Map<String,Object> getOrderList(OrderSearchVO vo) {
        Map<String,Object> map=new HashMap<String,Object>();
        //默认情况下第一页，每页10条数据
        if(null==vo.getPage()|| 0==vo.getPage()){
            vo.setPage(1);
        }
        if(null==vo.getPageSize()|| 0==vo.getPageSize()){
            vo.setPageSize(10);
        }

        vo.setStartIndex( (vo.getPage()-1)*vo.getPageSize());
        List<OrderVO> dataList = orderRepository.getOrderList(vo);
        List<OrderItemVO> orderItemList;
        List<Integer> orderIds = new ArrayList<Integer>();
        if(!dataList.isEmpty()) {
            for(OrderVO order:dataList) {
                orderIds.add(order.getId());
            }
        }

        // 获取每一个订单的明细列表
        if(!dataList.isEmpty()) {
            OrderSearchVO search = new OrderSearchVO();
            search.setOrderIds(orderIds);
            orderItemList = orderRepository.getOrderDetail(search);

            if(!orderItemList.isEmpty()) {
                List<OrderItemVO> list;
                for(OrderVO order:dataList) {
                    int id = order.getId();
                    list = new ArrayList<OrderItemVO>();
                    for(OrderItemVO item:orderItemList) {
                        if(item.getOrderId() == id) {
                            list.add(item);
                        }
                    }
                    if(!list.isEmpty()) {
                        order.setOrderItemVOList(list);
                    }
                }
            }
        }
        PageModel pageInfo = new PageModel<OrderVO>(dataList, vo.getPage(), vo.getPageSize());
        map.put("dataList",dataList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public OrderVO getOrderDetail(OrderSearchVO vo) {
        vo.setStartIndex(0);
        vo.setPageSize(1);
        OrderVO order = orderRepository.getOrderList(vo).get(0);
        order.setOrderItemVOList(orderRepository.getOrderDetail(vo));
        return order;
    }


}
