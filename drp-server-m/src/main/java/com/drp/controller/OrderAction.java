package com.drp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.entity.DAddressEntity;
import com.drp.entity.OOrderEntity;
import com.drp.entity.OOrderItemEntity;
import com.drp.service.OrderService;
import com.drp.vo.OrderVO;
import com.drp.vo.ShoppingCartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderAction {
    @Autowired(required = true)
    private OrderService orderService;

    /**
     * 生成订单
     *
     * @return
     */
    @RequestMapping(value = "generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<String> generateOrder(@RequestBody String jsonString) {
        BaseModel<String> model = new BaseModel<String>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer type = object.getInteger("type");
        Integer distributorId = object.getInteger("distributorId");
        String buyerMessage = object.getString("buyerMessage");
        JSONObject address = object.getJSONObject("address");
        DAddressEntity addressVO = JSONObject.toJavaObject(address, DAddressEntity.class);
        String string = object.getString("shoppingCartItemVOList");
        List<ShoppingCartItemVO> shoppingCartItemVOList = JSON.parseArray(string, ShoppingCartItemVO.class);

        try {
            String data =  orderService.generateOrder(type, addressVO, shoppingCartItemVOList, distributorId, buyerMessage);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 支付订单
     *
     * @return
     */
    @RequestMapping(value = "payOrder", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> payOrder(@RequestBody String jsonString) {
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        JSONObject order = object.getJSONObject("order");
        OOrderEntity orderVO = JSONObject.toJavaObject(order, OOrderEntity.class);

        try {
            Integer data =  orderService.payOrder(orderVO);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 批量取消订单
     *
     * @return
     */
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<String> cancelOrder(@RequestBody String jsonString) {
        BaseModel<String> model = new BaseModel<String>();
        JSONObject object = JSON.parseObject(jsonString);
        String string = object.getString("orderList");
        List<OOrderEntity> orderList = JSON.parseArray(string, OOrderEntity.class);
        try {
            String data =  orderService.cancelOrderList(orderList);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取订单明细
     *
     * @return
     */
    @RequestMapping(value = "getOrderDetailByOrderCode", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<OrderVO> getOrderDetailByOrderCode(@RequestBody String jsonString) {
        BaseModel<OrderVO> model = new BaseModel<OrderVO>();
        JSONObject object = JSON.parseObject(jsonString);
        String orderCode = object.getString("orderCode");
        try {
            OrderVO data =  orderService.getOrderDetailByOrderCode(orderCode);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }


}

