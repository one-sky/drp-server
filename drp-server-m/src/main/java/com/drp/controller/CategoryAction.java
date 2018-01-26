package com.drp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.Util.PageModel;
import com.drp.entity.PCategoryEntity;
import com.drp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("category")
public class CategoryAction {
    @Autowired(required = true)
    private CategoryService categoryService;

    /**
     * 获取类目
     *
     * @return
     */
    @RequestMapping(value = "getCategoryList", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel<List<PCategoryEntity>> getCategoryList() {
        BaseModel<List<PCategoryEntity>> model = new BaseModel<List<PCategoryEntity>>();
        try {
            List<PCategoryEntity> data = categoryService.getCategoryList();
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取类目详情by id
     * @return
     */
    @RequestMapping(value = "getCategoryById", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<PCategoryEntity> getCategoryById(@RequestBody String jsonString) {
        BaseModel<PCategoryEntity> model = new BaseModel<PCategoryEntity>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer id = object.getInteger("id");
        try {
            PCategoryEntity data =  categoryService.getCategoryById(id);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 获取类目详情by parentId
     * @return
     */
    @RequestMapping(value = "getCategoryByParentId", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<PCategoryEntity>> getCategoryByParentId(@RequestBody String jsonString) {
        BaseModel<List<PCategoryEntity>> model = new BaseModel<List<PCategoryEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer parentId = object.getInteger("parentId");
        try {
            List<PCategoryEntity> data =  categoryService.getCategoryByParentId(parentId);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }


    /**
     * 获取类目详情by categoryName
     * @return
     */
    @RequestMapping(value = "getCategoryByCategoryName", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<List<PCategoryEntity>> getCategoryByCategoryName(@RequestBody String jsonString) {
        BaseModel<List<PCategoryEntity>> model = new BaseModel<List<PCategoryEntity>>();
        JSONObject object = JSON.parseObject(jsonString);
        String categoryName = object.getString("categoryName");
        Integer pageNum = object.getInteger("pageNum");
        Integer pageSize = object.getInteger("pageSize");
        try {
            Map<String,Object> map =  categoryService.getCategoryByCategoryName(categoryName, pageNum, pageSize);
            List<PCategoryEntity> data= (List<PCategoryEntity>) map.get("dataList");
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
     * 保存类目
     * @return
     */
    @RequestMapping(value="saveCategory", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<Integer> saveCategory(@RequestBody String jsonString){
        BaseModel<Integer> model=new BaseModel<Integer>();
        JSONObject object = JSON.parseObject(jsonString);
        Integer userId = object.getInteger("userId");
        PCategoryEntity entity = JSON.parseObject(jsonString,PCategoryEntity.class);
        try {
            Integer result=categoryService.saveCategory(entity, userId);
            model.setData(result);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }


}

