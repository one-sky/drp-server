package com.drp.service.impl;

import com.drp.Util.BeanUtils;
import com.drp.Util.InitPage;
import com.drp.Util.PageModel;
import com.drp.entity.PCategoryEntity;
import com.drp.repository.CategoryRepository;
import com.drp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    protected CategoryRepository categoryRepository;

    public List<PCategoryEntity> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    public PCategoryEntity getCategoryById(Integer id) {
        return categoryRepository.getCategoryById(id);
    }

    public List<PCategoryEntity> getCategoryByParentId(Integer parentId) {
        return categoryRepository.getCategoryByParentId(parentId);
    }

    public Map<String, Object> getCategoryByCategoryName(String categoryName, Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        List<PCategoryEntity> dataList = categoryRepository.getCategoryByCategoryName(categoryName, initPageNum, initStartIndex);
        PageModel pageInfo = new PageModel<PCategoryEntity>(dataList, initPageNum, initPageSize);
        map.put("dataList",dataList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public Integer saveCategory(PCategoryEntity entity, Integer userId) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        Integer id= entity.getId();
        //新建时
        if(null == id || id ==0){
            PCategoryEntity parent=categoryRepository.getCategoryById(entity.getParentId());
            if(parent!=null) {
                entity.setStatus(0);//新建时设为无效状态
                entity.setIsLeaf(1);
                entity.setCreateBy(userId);
                entity.setLastUpdateBy(userId);
                entity.setLevel(String.valueOf(Integer.valueOf(parent.getLevel())+1));
                return categoryRepository.insertCategory(entity);
            }

        }else{
            PCategoryEntity dbCategory=categoryRepository.getCategoryById(id);
            BeanUtils.copyProperties(entity,dbCategory);
            dbCategory.setLastUpdateBy(userId);
            dbCategory.setLastUpdateTime(currentTime);
            return categoryRepository.updateCategory(entity);

        }
        return 0;
    }
}
