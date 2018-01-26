package com.drp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.Util.PageModel;
import com.drp.service.ProductService;
import com.drp.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("product")
public class ProductAction {
    @Autowired(required = true)
    private ProductService productService;

    /**
     * 获取商品列表
     *
     * @return
     */
    @RequestMapping(value = "getProductList", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<SpuSearchVO>> getProductList(@RequestBody String jsonString) {
        BaseModel<List<SpuSearchVO>> model = new BaseModel<List<SpuSearchVO>>();
        SpuSearchVO entity = JSON.parseObject(jsonString, SpuSearchVO.class);
        try {
            Map<String, Object> map = productService.getProductList(entity);
            List<SpuSearchVO> data = (List<SpuSearchVO>) map.get("dataList");
            PageModel pageInfo = (PageModel) map.get("pageInfo");
            model.setData(data);
            model.setPage(pageInfo);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取商品明细
     *
     * @return
     */
    @RequestMapping(value = "getSpuDetail", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<SpuDetailVO> getSpuDetail(@RequestBody String jsonString) {
        BaseModel<SpuDetailVO> model = new BaseModel<SpuDetailVO>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer spuId = object.getInteger("spuId");
        Integer distributorId=object.getInteger("distributorId");
        try {
            SpuDetailVO data =  productService.getSpuDetail(spuId, distributorId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }


}
