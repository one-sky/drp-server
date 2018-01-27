package com.drp.service;

import com.drp.entity.DAddressEntity;
import com.drp.entity.OOrderEntity;
import com.drp.vo.OrderItemVO;
import com.drp.vo.OrderSearchVO;
import com.drp.vo.OrderVO;
import com.drp.vo.ShoppingCartItemVO;

import java.util.List;
import java.util.Map;

public interface OrderService {

    // 生成订单
    public String generateOrder(Integer type, DAddressEntity addressVO,
                                 List<ShoppingCartItemVO> shoppingCartItemVOList, Integer distributorId,
                                 String buyerMessage);

    // 支付
    public Integer payOrder(OOrderEntity orderVO);

    // 根据id主键查询订单详情
    public List<OOrderEntity> searchOrderById(List<Integer> ids);

    // 取消订单
    public String cancelOrderList(List<OOrderEntity> orderList);

    // 获取订单列表
    public Map<String,Object> getOrderList(OrderSearchVO vo);

    // 获取订单明细
    public OrderVO getOrderDetail(OrderSearchVO vo);

}
