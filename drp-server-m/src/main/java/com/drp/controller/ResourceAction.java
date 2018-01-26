package com.drp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.Util.PageModel;
import com.drp.entity.RArticleEntity;
import com.drp.entity.RBannerEntity;
import com.drp.entity.RChannelResourceEntity;
import com.drp.service.ChannelService;
import com.drp.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("resource")
public class ResourceAction {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ChannelService channelService;

    /**
     * search导航
     * @return
     */
    @RequestMapping(value = "getBannerList", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<RBannerEntity>> getBannerList(@RequestBody String jsonString) {
        BaseModel<List<RBannerEntity>> model = new BaseModel<List<RBannerEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer status = object.getInteger("status") ;
        Integer pageNum = object.getInteger("pageNum");
        Integer pageSize = object.getInteger("pageSize");
        try {
            Map<String,Object> map =  resourceService.getBannerList(status, pageNum, pageSize);
            List<RBannerEntity> data= (List<RBannerEntity>) map.get("dataList");
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
     * save 导航
     * @return
     */
    @RequestMapping(value = "saveBanner", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveBanner(@RequestBody String jsonString) {
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer userId = object.getInteger("userId");
        RBannerEntity entity = JSON.parseObject(jsonString,RBannerEntity.class);
        try {
            Integer result=resourceService.saveBanner(entity, userId);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }
    /**
     * 获取评测、行业资讯list
     * @return
     */
    @RequestMapping(value = "getArticleList", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<RArticleEntity>> getArticleList(@RequestBody String jsonString) {
        BaseModel<List<RArticleEntity>> model = new BaseModel<List<RArticleEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer displayArea = object.getInteger("displayArea");
        Integer status = object.getInteger("status");
        Integer pageSize = object.getInteger("pageSize");
        Integer pageNum = object.getInteger("pageNum");
        try {
            Map<String,Object> map =  resourceService.getArticleList(displayArea, status, pageNum, pageSize);
            List<RArticleEntity> data= (List<RArticleEntity>) map.get("dataList");
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
     * save评测、行业资讯
     * @return
     */
    @RequestMapping(value = "saveArticle", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveArticle(@RequestBody String jsonString) {
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer userId = object.getInteger("userId");
        RArticleEntity entity = JSON.parseObject(jsonString,RArticleEntity.class);
        try {
            Integer result=resourceService.saveArticle(entity, userId);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取可选渠道列表
     * @return
     */
    @RequestMapping(value = "getChannelOptionList",method= RequestMethod.GET)
    @ResponseBody
    public BaseModel<List<RChannelResourceEntity>> getChannelOptionList(){
        BaseModel<List<RChannelResourceEntity>> result = new BaseModel<List<RChannelResourceEntity>>();

        List<RChannelResourceEntity> data =  channelService.getChannelOptionList();
        result.setData(data);
        return result;

    }

    /**
     * 保存渠道
     * @return
     */
    @RequestMapping(value = "saveChannelOption",method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveChannel(@RequestBody String jsonString){
        BaseModel<Integer> model = new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        RChannelResourceEntity entity = JSON.parseObject(jsonString,RChannelResourceEntity.class);
        try {
            Integer result=channelService.saveChannelOption(entity);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;

    }
}
