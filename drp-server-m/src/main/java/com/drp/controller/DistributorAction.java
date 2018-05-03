package com.drp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.Util.PageModel;
import com.drp.entity.*;
import com.drp.service.BrandService;
import com.drp.service.ChannelService;
import com.drp.service.DistributorService;
import com.drp.service.ProductService;
import com.drp.vo.CollectProductVO;
import com.drp.vo.SearchVO;
import com.drp.vo.ShoppingCartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("distributor")
public class DistributorAction {

    @Autowired
    private DistributorService distributorService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "getDistributorDetail", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<DDistributorEntity> getDistributorDetail(@RequestBody String jsonString) {
        BaseModel<DDistributorEntity> model = new BaseModel<DDistributorEntity>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer id = object.getInteger("id");
        try {
            DDistributorEntity data =  distributorService.getDistributorDetail(id);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    @RequestMapping(value = "getAddressList", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<DAddressEntity>> getAddressList(@RequestBody String jsonString) {
        BaseModel<List<DAddressEntity>> model = new BaseModel<List<DAddressEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer id = object.getInteger("id") ;
        try {
            Map<String,Object> map =  distributorService.getAddressList(id);
            List<DAddressEntity> data= (List<DAddressEntity>) map.get("dataList");
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    @RequestMapping(value = "getAddress", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<DAddressEntity> getAddress(@RequestBody String jsonString) {
        BaseModel<DAddressEntity> model = new BaseModel<DAddressEntity>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer id = object.getInteger("id") ;
        try {
            DAddressEntity data =  distributorService.getAddress(id);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }


    @RequestMapping(value = "saveAddress",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveAddress(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        DAddressEntity entity = JSON.parseObject(jsonString,DAddressEntity.class);
        try {
            Integer result=distributorService.saveAddress(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }

    @RequestMapping(value = "deleteAddress",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> deleteAddress(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer id =  object.getInteger("id");
        Integer distributorId =  object.getInteger("distributorId");
        try {
            Integer data = distributorService.deleteAddress(id,distributorId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }

    @RequestMapping(value = "setAddressDefault",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> setIdDsetAddressDefaultefault(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer id =  object.getInteger("id");
        Integer distributorId =  object.getInteger("distributorId");
        try {
            Integer data = distributorService.setAddressDefault(id,distributorId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }


    @RequestMapping(value = "getPoints",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<DPointsHistoryEntity>> getPoints(@RequestBody String jsonString){
        BaseModel<List<DPointsHistoryEntity>> model = new BaseModel<List<DPointsHistoryEntity>>();
        SearchVO entity = JSON.parseObject(jsonString, SearchVO.class);
        try {
            Map<String, Object> map = distributorService.getPointList(entity);
            List<DPointsHistoryEntity> data = (List<DPointsHistoryEntity>) map.get("dataList");
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
     * 根据distributorId 和 品牌name 获取分销商同一品牌的不同代理
     * @return
     */
    @RequestMapping(value = "getBrandListByAgentBrand", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<RBrandEntity>> getBrandListByAgentBrand(@RequestBody String jsonString) {
        BaseModel<List<RBrandEntity>> model = new BaseModel<List<RBrandEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer distributorId = object.getInteger("distributorId");
        String status = object.getString("status");
        String brandName = object.getString("brandName");
        Integer pageSize = object.getInteger("pageSize");
        Integer pageNum = object.getInteger("pageNum");
        try {
            Map<String,Object> map =  brandService.getBrandListByAgentBrand(distributorId, brandName, status, pageNum, pageSize);
            List<RBrandEntity> data= (List<RBrandEntity>) map.get("dataList");
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
     * 根据distributorId 和 品牌id获取代理情况
     * @return
     */
    @RequestMapping(value = "getAgentBrand",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<DAgentBrandEntity> getAgentBrand(@RequestBody String jsonString){
        BaseModel<DAgentBrandEntity> model = new BaseModel<DAgentBrandEntity>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer distributorId = object.getInteger("distributorId");
        Integer brandId = object.getInteger("brandId");
        try {
            DAgentBrandEntity data = brandService.getAgentBrand(distributorId, brandId);

            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 申请代理
     * @return
     */
    @RequestMapping(value = "insertAgentBrand",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> insertAgentBrand(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        DAgentBrandEntity entity = JSON.parseObject(jsonString,DAgentBrandEntity.class);
        try {
            Integer data = brandService.insertAgentBrand(entity);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }

    /**
     *  获取已代理渠道列表
     * @return
     */
    @RequestMapping(value = "getChannelList",method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<DChannelEntity>> getChannelList(@RequestBody String jsonString){
        BaseModel<List<DChannelEntity>> model = new BaseModel<List<DChannelEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer distributorId = object.getInteger("distributorId");
        try {
            List<DChannelEntity> data = channelService.getChannelList(distributorId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }

    /**
     *  获取已代理渠道的代理的品牌
     * @return
     */
    @RequestMapping(value = "getAgentBrandByChannel",method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<RBrandEntity>> getAgentBrandByChannel(@RequestBody String jsonString){
        BaseModel<List<RBrandEntity>> model = new BaseModel<List<RBrandEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer distributorId = object.getInteger("distributorId");
        Integer channelId = object.getInteger("channelId");
        try {
            List<RBrandEntity> data = brandService.getAgentBrandByChannel(distributorId, channelId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }

    /**
     *  获取已代理渠道
     * @return
     */
    @RequestMapping(value = "getChannel",method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<DChannelEntity> getChannel(@RequestBody String jsonString){
        BaseModel<DChannelEntity> model = new BaseModel<DChannelEntity>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer id = object.getInteger("id");
        try {
            DChannelEntity data = channelService.getChannelById(id);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }

    /**
     *  申请渠道代理
     * @return
     */
    @RequestMapping(value = "saveChannel",method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveChannel(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        DChannelEntity entity = JSON.parseObject(jsonString,DChannelEntity.class);
        try {
            Integer data = channelService.saveChannel(entity);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }

    /**
     * 获取已关注商品列表
     * @return
     */
    @RequestMapping(value="getCollectionProductList",method=RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<CollectProductVO>>  getCollectionProductList(@RequestBody String jsonString){
        BaseModel<List<CollectProductVO>> model = new BaseModel<List<CollectProductVO>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer distributorId = object.getInteger("distributorId");
        Integer pageNum = object.getInteger("pageNum");
        Integer pageSize = object.getInteger("pageSize");
        try {
            Map<String, Object> map = productService.getCollectionProductList(distributorId, pageNum, pageSize);
            List<CollectProductVO> data = (List<CollectProductVO>) map.get("dataList");
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
     * 批量取消已关注商品
     * @return
     */
    @RequestMapping(value="cancelCollectionProduct",method=RequestMethod.POST)
    @ResponseBody
    public  BaseModel<Integer> cancelCollectionProduct(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer distributorId = object.getInteger("distributorId");
        List<Integer> ids =  (List)object.getJSONArray("ids");
        try {
            Integer data = productService.cancelCollectionProduct(distributorId, ids);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }

        return model;
    }

    /**
     * 批量关注商品
     * @return
     */
    @RequestMapping(value="addCollectionProduct",method=RequestMethod.POST)
    @ResponseBody
    public  BaseModel<Integer> addCollectionProduct(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer distributorId = object.getInteger("distributorId");
        List<Integer> spuIds =  (List)object.getJSONArray("spuIds");
        try {
            Integer data = productService.addCollectionProduct(distributorId, spuIds);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }

        return model;
    }

    /**
     * 获取购物车列表
     * @return
     */
    @RequestMapping(value="getShoppingCartList",method=RequestMethod.POST)
    @ResponseBody
    public  BaseModel<List<ShoppingCartItemVO>> getShoppingCartList(@RequestBody String jsonString){
        BaseModel<List<ShoppingCartItemVO>> model = new BaseModel<List<ShoppingCartItemVO>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer distributorId ;
        Integer type;
        if(object.containsKey("type")) {
            type = object.getInteger("type");
        }else {
            type = null;
        }
        if(object.containsKey("distributorId")) {
            distributorId = object.getInteger("distributorId");
        }else {
            distributorId = null;
        }

        try {
            List<ShoppingCartItemVO> data = productService.getShoppingCartList(distributorId, type);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }

        return model;
    }

    /**
     *  加入或购物车中减少删除等
     * @return
     */
    @RequestMapping(value="updateShoppingCartList",method=RequestMethod.POST)
    @ResponseBody
    public  BaseModel<Integer> updateShoppingCartList(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer distributorId = object.getInteger("distributorId");
        String string = object.getString("shoppingCartItemVOList");
        List<ShoppingCartItemVO> shoppingCartItemVOList = JSON.parseArray(string, ShoppingCartItemVO.class);
        try {
            Integer data = productService.updateShoppingCartList(shoppingCartItemVOList, distributorId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }

        return model;
    }
}
