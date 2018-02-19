package com.drp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.Util.PageModel;
import com.drp.entity.DDistributorEntity;
import com.drp.entity.RBrandEntity;
import com.drp.service.ManageService;
import com.drp.vo.AgentBrandVO;
import com.drp.vo.ManageUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("manage")
public class ManageAction {

    @Autowired
    private ManageService manageService;


    @RequestMapping(value = "getUserList", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<ManageUserVO>> getUserList(@RequestBody String jsonString) {
        BaseModel<List<ManageUserVO>> model = new BaseModel<List<ManageUserVO>>();
        JSONObject object = JSON.parseObject(jsonString);
        ManageUserVO entity = JSON.parseObject(jsonString,ManageUserVO.class);
        Integer pageNum = object.getInteger("pageNum");
        Integer pageSize = object.getInteger("pageSize");
        try {
            Map<String, Object>  map =  manageService.getUserList(entity, pageNum, pageSize);
            List<ManageUserVO> data = (List<ManageUserVO>) map.get("dataList");
            PageModel pageInfo = (PageModel) map.get("pageInfo");
            model.setData(data);
            model.setPage(pageInfo);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    @RequestMapping(value = "updatePoint", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> updatePoint(@RequestBody String jsonString) {
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer userId = object.getInteger("userId");
        Integer point = object.getInteger("point");
        String reason = object.getString("reason");
        try {
            Integer  data =  manageService.updatePoint(userId, point, reason);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取品牌
     * @return
     */
    @RequestMapping(value="getBrandList",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<RBrandEntity>> getBrandList(@RequestBody String jsonString){
        BaseModel<List<RBrandEntity>> model = new BaseModel<List<RBrandEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        RBrandEntity entity = JSON.parseObject(jsonString,RBrandEntity.class);
        Integer pageNum = object.getInteger("pageNum");
        Integer pageSize = object.getInteger("pageSize");
        try {
            Map<String, Object>  map =  manageService.getBrandList(entity, pageNum, pageSize);
            List<RBrandEntity> data = (List<RBrandEntity>) map.get("dataList");
            PageModel pageInfo = (PageModel) map.get("pageInfo");
            model.setData(data);
            model.setPage(pageInfo);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    @RequestMapping(value = "saveBrand",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveAddress(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        RBrandEntity entity = JSON.parseObject(jsonString,RBrandEntity.class);
        try {
            Integer result=manageService.saveBrand(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }

    /**
     * 删除品牌
     * @return
     */
    @RequestMapping(value="deleteBrand",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> deleteBrand(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer brandId = object.getInteger("brandId");
        try {
            Integer data =  manageService.deleteBrand(brandId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取品牌代理
     * @return
     */
    @RequestMapping(value="getAgentBrandList",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<AgentBrandVO>> getAgentBrandList(@RequestBody String jsonString){
        BaseModel<List<AgentBrandVO>> model = new BaseModel<List<AgentBrandVO>>();
        JSONObject object = JSON.parseObject(jsonString);
        AgentBrandVO entity = JSON.parseObject(jsonString,AgentBrandVO.class);
        Integer pageNum = object.getInteger("pageNum");
        Integer pageSize = object.getInteger("pageSize");
        try {
            Map<String, Object>  map =  manageService.getAgentBrandList(entity, pageNum, pageSize);
            List<AgentBrandVO> data = (List<AgentBrandVO>) map.get("dataList");
            PageModel pageInfo = (PageModel) map.get("pageInfo");
            model.setData(data);
            model.setPage(pageInfo);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }
}