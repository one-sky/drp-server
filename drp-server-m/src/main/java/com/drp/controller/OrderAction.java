package com.drp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.Util.PageModel;
import com.drp.entity.DAddressEntity;
import com.drp.entity.OOrderEntity;
import com.drp.entity.OOrderItemEntity;
import com.drp.service.OrderService;
import com.drp.vo.OrderSearchVO;
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
import java.util.Map;

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
        OOrderEntity orderVO = JSON.parseObject(jsonString, OOrderEntity.class);

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
        List<OOrderEntity> orderList = JSON.parseArray(jsonString, OOrderEntity.class);
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
     * 获取订单列表
     *
     * @return
     */
    @RequestMapping(value = "getOrderList", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<OrderVO>> getOrderList(@RequestBody String jsonString) {
        BaseModel<List<OrderVO>> model= new BaseModel<List<OrderVO>>();
        OrderSearchVO vo = JSON.parseObject(jsonString, OrderSearchVO.class);
        try {
            Map<String,Object> map =  orderService.getOrderList(vo);
            List<OrderVO> data= (List<OrderVO>) map.get("dataList");
            PageModel pageInfo= (PageModel) map.get("pageInfo");
            model.setData(data);
            model.setPage(pageInfo);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取订单明细
     *
     * orderCode 或者orderIds其中一个
     * @return
     */
    @RequestMapping(value = "getOrderDetail", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<OrderVO> getOrderDetail(@RequestBody String jsonString) {
        BaseModel<OrderVO> model = new BaseModel<OrderVO>();
        OrderSearchVO vo = JSON.parseObject(jsonString, OrderSearchVO.class);
        try {
            OrderVO data =  orderService.getOrderDetail(vo);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }


}

