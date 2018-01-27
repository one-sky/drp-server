package com.drp.repository;

import com.drp.entity.OOrderEntity;
import com.drp.entity.OOrderItemEntity;
import com.drp.vo.OrderItemVO;
import com.drp.vo.OrderSearchVO;
import com.drp.vo.OrderVO;
import org.hibernate.criterion.Order;

import java.util.List;

public interface OrderRepository {
    public Integer insertOrder( OOrderEntity order);

    public Integer insertOrderItemList(List<OOrderItemEntity> orderItemVOList, Integer orderId);

    public Integer payOrder( OOrderEntity order);

    public List<OOrderEntity> searchOrderById(List<Integer> ids);

    public String cancelOrderList(List<OOrderEntity> orderList);

    public List<OrderVO> getOrderList(OrderSearchVO vo);

    public List<OrderItemVO> getOrderDetail(OrderSearchVO vo);
}
