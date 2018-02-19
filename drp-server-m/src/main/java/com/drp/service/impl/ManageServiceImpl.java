package com.drp.service.impl;

import com.drp.Util.BeanUtils;
import com.drp.Util.InitPage;
import com.drp.Util.PageModel;
import com.drp.entity.DDistributorEntity;
import com.drp.entity.DPointsEntity;
import com.drp.entity.DPointsHistoryEntity;
import com.drp.entity.RBrandEntity;
import com.drp.repository.BrandRepository;
import com.drp.repository.DistributorRepository;
import com.drp.repository.ManageRepository;
import com.drp.repository.ProductRepository;
import com.drp.service.ManageService;
import com.drp.vo.AgentBrandVO;
import com.drp.vo.ManageUserVO;
import com.drp.vo.SearchVO;
import com.drp.vo.SpuSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    protected ManageRepository manageRepository;

    @Autowired
    protected DistributorRepository distributorRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected BrandRepository brandRepository;

    public Map<String, Object> getUserList(ManageUserVO entity, Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        List<ManageUserVO> userList = manageRepository.getUserList(entity, initPageSize, initStartIndex);
        List<ManageUserVO> distributorList = new ArrayList<ManageUserVO>();
        for(ManageUserVO user:userList) {
            if(null!=user.getDistributorId()) {
                distributorList.add(user);
            }
        }
        List<ManageUserVO> vipList = manageRepository.getDistributorVip(distributorList);
        for(ManageUserVO user:userList) {
            // 已成为分销商不是用户了
            if(null!=user.getDistributorId())
            for(ManageUserVO vip:vipList) {
                if(vip.getDistributorId().equals(user.getDistributorId())) {
                    vip.setUserId(user.getUserId());
                    BeanUtils.copyProperties(vip, user);
                    break;
                }
            }
        }
        PageModel pageInfo = new PageModel<ManageUserVO>(userList, initPageNum, initPageSize);
        map.put("dataList",userList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public Integer updatePoint(Integer userId, Integer point, String reason) {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        ManageUserVO entity = new ManageUserVO();
        entity.setUserId(userId);
        ManageUserVO dbEntity = manageRepository.getUserList(entity, 1, 0).get(0);
        if(null == dbEntity.getPoints()) {
            return -100;

        }
        if(dbEntity.getPoints() + point < 0) {
            return -10;
        }
        // 更新分销商信息中的积分
        dbEntity.setPoints(dbEntity.getPoints() + point);
        dbEntity.setLastUpdateTime(currentTime);
        DDistributorEntity distributorEntity = new DDistributorEntity();
        BeanUtils.copyProperties(dbEntity, distributorEntity);
        distributorEntity.setId(dbEntity.getDistributorId());
        distributorRepository.updateDistributor(distributorEntity);

        Integer distributorId = dbEntity.getDistributorId();
        DPointsHistoryEntity pointsHistoryEntity = new DPointsHistoryEntity();
        pointsHistoryEntity.setDistributorId(distributorId);
        // 系统修改积分
        pointsHistoryEntity.setPointsType("2");

        SearchVO vo = new SearchVO();
        vo.setDistributorId(distributorId);
        vo.setStartIndex(0);
        vo.setPageSize(1);
        List<DPointsHistoryEntity> dbPoint = distributorRepository.getPointList(vo);
        if(dbPoint.isEmpty()) {
            pointsHistoryEntity.setPointsBefore(0);
        } else {
            pointsHistoryEntity.setPointsBefore((dbPoint.get(0)).getPointsAfter());
        }
        pointsHistoryEntity.setPoints(point);
        pointsHistoryEntity.setPointsAfter(point + pointsHistoryEntity.getPointsBefore());
        pointsHistoryEntity.setCreateBy(999);
        pointsHistoryEntity.setCreateTime(currentTime);
        pointsHistoryEntity.setLastUpdateBy(999);
        pointsHistoryEntity.setLastUpdateTime(currentTime);
        pointsHistoryEntity.setReason(reason);
        return distributorRepository.insertPoint(pointsHistoryEntity);

    }

    public Map<String, Object> getBrandList(RBrandEntity entity, Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        List<RBrandEntity> brandList = manageRepository.getBrandList(entity, initPageSize, initStartIndex);
        PageModel pageInfo = new PageModel<RBrandEntity>(brandList, initPageNum, initPageSize);
        map.put("dataList",brandList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public Integer saveBrand(RBrandEntity entity) {
        Timestamp tmp = new Timestamp(new Date().getTime());
        Integer id = entity.getId();
        //新建时
        if (null == id || id == 0) {
            entity.setCreateBy("999");
            entity.setCreateTime(tmp);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(tmp);
            entity.setIsFinished("Y");
            return manageRepository.insertBrand(entity);
        } else {
            // 若修改了品牌所属类目，需要判断该品牌下是否有商品，否则类目不一致
            RBrandEntity dbBrand = brandRepository.getBrandById(entity.getId());
            if(dbBrand.getCategoryId() != entity.getCategoryId()) {
                SpuSearchVO vo = new SpuSearchVO();
                List<Integer> brandIds = new ArrayList<Integer>();
                List<Integer> categoryIds = new ArrayList<Integer>();
                brandIds.add(dbBrand.getId());
                categoryIds.add(dbBrand.getCategoryId());
                vo.setBrandIds(brandIds);
                vo.setCategoryIds(categoryIds);
                vo.setStartIndex(0);
                vo.setPageSize(1);
                if(!productRepository.getProductList(vo).isEmpty()) {
                    return -10;
                }
            }
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(tmp);
            return manageRepository.updateBrand(entity);
        }
    }

    public Integer deleteBrand(Integer brandId) {
        SpuSearchVO vo = new SpuSearchVO();
        List<Integer> brandIds = new ArrayList<Integer>();
        brandIds.add(brandId);
        vo.setBrandIds(brandIds);
        vo.setStartIndex(0);
        vo.setPageSize(1);
        if(!productRepository.getProductList(vo).isEmpty()) {
            return -10;
        }
        return manageRepository.deleteBrand(brandId);
    }

    public Map<String, Object> getAgentBrandList(AgentBrandVO entity, Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        List<AgentBrandVO> brandList = manageRepository.getAgentBrandList(entity, initPageSize, initStartIndex);
        PageModel pageInfo = new PageModel<AgentBrandVO>(brandList, initPageNum, initPageSize);
        map.put("dataList",brandList);
        map.put("pageInfo",pageInfo);
        return map;
    }
}

