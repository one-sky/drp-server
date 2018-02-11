package com.drp.repository;

import com.drp.entity.PAttrValueEntity;
import com.drp.entity.PCategoryAttrEntity;
import com.drp.entity.PCategoryEntity;
import com.drp.vo.AttrVO;

import java.util.List;

public interface CategoryRepository {

    public List<PCategoryEntity> getCategoryList(Integer userType);

    public PCategoryEntity getCategoryById(Integer id);

    public List<PCategoryAttrEntity> getAttributeList(Integer userType, List<Integer> categoryIds);

    public List<PAttrValueEntity> getAttrValueList(Integer userType, List<Integer> attrIds);

    public List<PCategoryEntity> getCategoryByParentId(Integer parentId);

    public List<PCategoryEntity> getCategoryByCategoryName(String categoryName, Integer pageSize, Integer startIndex);

    public Integer insertCategory(PCategoryEntity entity);

    public Integer updateCategory(PCategoryEntity entity);
}
