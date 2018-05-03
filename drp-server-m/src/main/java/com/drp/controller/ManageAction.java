package com.drp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.Util.PageModel;
import com.drp.entity.*;
import com.drp.service.ManageService;
import com.drp.vo.AgentBrandVO;
import com.drp.vo.ManageUserVO;
import com.drp.vo.OrderSearchVO;
import com.drp.vo.OrderVO;
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

    /**
     * 审核品牌代理
     * @return
     */
    @RequestMapping(value="updateAgentStatus",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> updateAgentStatus(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        DAgentBrandEntity entity = JSON.parseObject(jsonString,DAgentBrandEntity.class);
        try {
            Integer  data =  manageService.updateAgentStatus(entity);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 保存类目
     * @return
     */
    @RequestMapping(value="saveCategory", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveCategory(@RequestBody String jsonString){
        BaseModel<Integer> model=new BaseModel<Integer>();
        PCategoryEntity entity = JSON.parseObject(jsonString,PCategoryEntity.class);
        try {
            Integer result=manageService.saveCategory(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 删除类目
     * @return
     */
    @RequestMapping(value="deleteCategory", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> deleteCategory(@RequestBody String jsonString){
        BaseModel<Integer> model=new BaseModel<Integer>();
        PCategoryEntity entity = JSON.parseObject(jsonString,PCategoryEntity.class);
        try {
            Integer result=manageService.deleteCategory(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取属性大类
     * @return
     */
    @RequestMapping(value="saveAttr", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveAttr(@RequestBody String jsonString){
        BaseModel<Integer> model=new BaseModel<Integer>();
        PCategoryAttrEntity entity = JSON.parseObject(jsonString,PCategoryAttrEntity.class);
        try {
            Integer result=manageService.saveAttr(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 删除属性大类
     * @return
     */
    @RequestMapping(value="deleteAttr", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> deleteAttr(@RequestBody String jsonString){
        BaseModel<Integer> model=new BaseModel<Integer>();
        PCategoryAttrEntity entity = JSON.parseObject(jsonString,PCategoryAttrEntity.class);
        try {
            Integer result=manageService.deleteAttr(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 保存 子属性
     * @return
     */
    @RequestMapping(value="saveAttrValue", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveAttrValue(@RequestBody String jsonString){
        BaseModel<Integer> model=new BaseModel<Integer>();
        PAttrValueEntity entity = JSON.parseObject(jsonString,PAttrValueEntity.class);
        try {
            Integer result=manageService.saveAttrValue(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 删除属性大类
     * @return
     */
    @RequestMapping(value="deleteAttrValue", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> deleteAttrValue(@RequestBody String jsonString){
        BaseModel<Integer> model=new BaseModel<Integer>();
        PAttrValueEntity entity = JSON.parseObject(jsonString,PAttrValueEntity.class);
        try {
            Integer result=manageService.deleteAttrValue(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    @RequestMapping(value = "saveProduct",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveProduct(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        PProductCategoryEntity entity = JSON.parseObject(jsonString,PProductCategoryEntity.class);
        try {
            Integer result=manageService.saveProduct(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }

    /**
     * 保存sku
     * @return
     */
    @RequestMapping(value="saveSku", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveSku(@RequestBody String jsonString){
        BaseModel<Integer> model=new BaseModel<Integer>();
        PProductSkuEntity entity = JSON.parseObject(jsonString,PProductSkuEntity.class);
        JSONObject object = JSON.parseObject(jsonString);
        String priceListString = object.getString("priceList");
        List<PProductQuantityEntity> priceList = JSON.parseArray(priceListString, PProductQuantityEntity.class);
        try {
            Integer result=manageService.saveSku(entity, priceList);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 保存sku价格列表
     * @return
     */
    @RequestMapping(value="savePriceList", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> savePriceList(@RequestBody String jsonString){
        BaseModel<Integer> model=new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer skuId = object.getInteger("skuId");
        String priceList = object.getString("priceList");
        List<PProductQuantityEntity> entity = JSON.parseArray(priceList, PProductQuantityEntity.class);
        try {
            Integer result=manageService.savePriceList(skuId, entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 修改快递信息
     *
     * @return
     */
    @RequestMapping(value = "updateOrder", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> updateOrder(@RequestBody String jsonString) {
        BaseModel<Integer> model = new BaseModel<Integer>();
        OOrderEntity order = JSON.parseObject(jsonString, OOrderEntity.class);
        try {
            Integer data =  manageService.updateOrder(order);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 保存促销信息
     *
     * @return
     */
    @RequestMapping(value = "savePromotion", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> savePromotion(@RequestBody String jsonString) {
        BaseModel<Integer> model = new BaseModel<Integer>();
        PProductPromotionEntity entity = JSON.parseObject(jsonString, PProductPromotionEntity.class);
        try {
            Integer data =  manageService.savePromotion(entity);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 保存促销商品价格信息
     *
     * @return
     */
    @RequestMapping(value = "savePromotionPriceList", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> savePromotionPriceList(@RequestBody String jsonString) {
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer skuId = object.getInteger("skuId");
        Integer promotionId = object.getInteger("promotionId");
        String priceList = object.getString("priceList");
        List<PPromoteProductEntity> entity = JSON.parseArray(priceList, PPromoteProductEntity.class);
        try {
            Integer data =  manageService.savePromotionPriceList(promotionId, skuId, entity);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 保存特殊商品价格信息
     *
     * @return
     */
    @RequestMapping(value = "saveSpecialPriceList", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveSpecialPriceList(@RequestBody String jsonString) {
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer skuId = object.getInteger("skuId");
        Integer distributorId = object.getInteger("distributorId");
        String priceList = object.getString("priceList");
        List<PSkupriceDistributorEntity> entity = JSON.parseArray(priceList, PSkupriceDistributorEntity.class);
        try {
            Integer data =  manageService.saveSpecialPriceList(distributorId, skuId, entity);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

}