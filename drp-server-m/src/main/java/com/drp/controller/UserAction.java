package com.drp.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drp.Util.BaseModel;
import com.drp.Util.Constants;
import com.drp.entity.DDistributorEntity;
import com.drp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserAction {
    @Autowired(required=true)
    private UserService userService;

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<DDistributorEntity> login(@RequestBody String jsonString) {
        BaseModel<DDistributorEntity> model = new BaseModel<DDistributorEntity>();
        JSONObject object = JSON.parseObject(jsonString);
        String phone = object.getString("phone");
        String password = object.getString("password");
        try {
            DDistributorEntity data =  userService.login(phone, password);
            model.setData(data);
        } catch (Exception e) {
            model.setMessage(e.getMessage());
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public BaseModel<DDistributorEntity> register(@RequestBody String jsonString){
        BaseModel<DDistributorEntity> result = new BaseModel<DDistributorEntity>();

        JSONObject object = JSON.parseObject(jsonString);
        String phone = object.getString("phone");
        String password = object.getString("password");

        try {
            DDistributorEntity data =  userService.register(phone,password);
            result.setData(data);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return result;


    }
}
