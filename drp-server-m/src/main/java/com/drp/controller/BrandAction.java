package com.drp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.entity.RBrandAttachmentEntity;
import com.drp.entity.RBrandEntity;
import com.drp.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("brand")
public class BrandAction {
    @Autowired(required=true)
    private BrandService brandService;

    /**
     * 获取最新品牌 前5个
     * @return
     */
    @RequestMapping(value = "getNewBrandList", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel<List<RBrandEntity>> getNewBrandList() {
        BaseModel<List<RBrandEntity>> model = new BaseModel<List<RBrandEntity>>();
        try {
            List<RBrandEntity> data =  brandService.getNewBrandList();
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取品牌详情
     * @return
     */
    @RequestMapping(value = "getBrandById", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<RBrandEntity> getBrandById(@RequestBody String jsonString) {
        BaseModel<RBrandEntity> model = new BaseModel<RBrandEntity>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer id = object.getInteger("id");
        try {
            RBrandEntity data =  brandService.getBrand(id);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 根据一级类目id获取品牌列表
     * @return
     */
    @RequestMapping(value="getBrandListByCategoryId",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<RBrandEntity>> getBrandListByCategoryId(@RequestBody String jsonString){
        BaseModel<List<RBrandEntity>> model = new BaseModel<List<RBrandEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer categoryId = object.getInteger("categoryId");
        try {
            List<RBrandEntity> data =  brandService.getBrandListByCategoryId(categoryId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取品牌的下载资料包
     * @return
     */
    @RequestMapping(value = "getBrandAttachment", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<RBrandAttachmentEntity> getBrandAttachment(@RequestBody String jsonString) {
        BaseModel<RBrandAttachmentEntity> model = new BaseModel<RBrandAttachmentEntity>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer brandId = object.getInteger("brandId");
        try {
            RBrandAttachmentEntity data =  brandService.getBrandAttachment(brandId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }
}
