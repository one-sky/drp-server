package com.drp.service.impl;

import com.drp.Util.BeanUtils;
import com.drp.Util.InitPage;
import com.drp.Util.PageModel;
import com.drp.entity.PAttrValueEntity;
import com.drp.entity.PCategoryAttrEntity;
import com.drp.entity.PCategoryEntity;
import com.drp.repository.CategoryRepository;
import com.drp.service.CategoryService;
import com.drp.vo.AttrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;

import java.sql.Timestamp;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    protected CategoryRepository categoryRepository;

    public List<PCategoryEntity> getCategoryList(Integer userType) {
        return categoryRepository.getCategoryList(userType);
    }

    public PCategoryEntity getCategoryById(Integer id) {
        return categoryRepository.getCategoryById(id);
    }

    public List<AttrVO> getAttributeList(Integer userType, List<Integer> categoryIds) {
        List<AttrVO> result = new ArrayList<AttrVO>();
        List<PCategoryAttrEntity> attrList = categoryRepository.getAttributeList(userType, categoryIds);
        List<Integer> attrIds = new ArrayList<Integer>();
        if(!attrList.isEmpty()) {
            for(PCategoryAttrEntity vo : attrList) {
                AttrVO a = new AttrVO();
                BeanUtils.copyProperties(vo, a);
                result.add(a);
                attrIds.add(vo.getId());
            }
        }
        List<PAttrValueEntity> attrValueEntities = new ArrayList<PAttrValueEntity>();
        if(!attrIds.isEmpty()) {
            attrValueEntities = categoryRepository.getAttrValueList(userType, attrIds);
        }

        if(!attrValueEntities.isEmpty()) {
            List<PAttrValueEntity> attrs;
            for(AttrVO vo : result) {
                attrs = new ArrayList<PAttrValueEntity>();
                Integer id = vo.getId();
                for(PAttrValueEntity entity:attrValueEntities) {
                    if(entity.getAttrId() == id) {
                        attrs.add(entity);
                    }
                }
                vo.setAttrValueEntityList(attrs);
                if(attrs.isEmpty()) {
                    vo.setStatus(2);
                } else {
                    vo.setStatus(1);
                }

            }
        }

        return result;

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
}
