package com.drp.service;

import com.drp.entity.PCategoryEntity;
import com.drp.vo.AttrVO;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    public List<PCategoryEntity> getCategoryList(Integer userType);

    public PCategoryEntity getCategoryById(Integer id);

    public List<AttrVO> getAttributeList(Integer userType, List<Integer> categoryIds);

    public List<PCategoryEntity> getCategoryByParentId(Integer parentId);

    public Map<String,Object> getCategoryByCategoryName(String categoryName, Integer pageNum, Integer pageSize);

    public Integer saveCategory(PCategoryEntity entity, Integer userId);

}
