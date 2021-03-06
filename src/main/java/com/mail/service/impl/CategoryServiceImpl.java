package com.mail.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mail.common.ServerResponse;
import com.mail.dao.CategoryMapper;
import com.mail.pojo.Category;
import com.mail.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Set;

/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName CategoryServiceImpl.java
 * @Description TODO
 * @createTime 2018年11月26日 17:27:00
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

        private Logger logger=LoggerFactory.getLogger(CategoryServiceImpl.class);

        @Autowired
        private CategoryMapper categoryMapper;

        public ServerResponse addCategory(String categoryName,Integer parentId ){
                if(parentId==null|| StringUtils.isBlank(categoryName)){
                        return  ServerResponse.createByErrorMessage("添加品类参数错误");
                }
                Category category=new Category();
                category.setName(categoryName);
                category.setParentId(parentId);
                category.setStatus(true);
                int rowCount=categoryMapper.insertSelective(category);
                if(rowCount>0){
                        return  ServerResponse.createBySuccess("添加品类成功");
                }
                return ServerResponse.createByErrorMessage("添加品类失败");

        }

        public ServerResponse setCategoryName(Integer categoryId,String categoryName){
                if (categoryId==null||StringUtils.isBlank(categoryName)){
                        return  ServerResponse.createByErrorMessage("添加品类参数错误");
                }
                Category category=new Category();
                category.setId(categoryId);
                category.setName(categoryName);
                int rowCount=categoryMapper.updateByPrimaryKeySelective(category);
                if(rowCount>0){
                        return  ServerResponse.createBySuccess("更新品类成功");
                }
                return ServerResponse.createByErrorMessage("更新品类失败");
        }

        public ServerResponse<List<Category>>getChildrenParallelCategory(Integer categoryId){
                List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
                if(CollectionUtils.isEmpty(categoryList)){
                        logger.info("未找到当前分类的子分类");
                }
                return ServerResponse.createBySuccess();
        }

        /**
         * 递归查询本节点的id及孩子节点的id
         * @param categoryId
         * @return
         */
        public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId){
                Set<Category> categorySet = Sets.newHashSet();
                findChildCategory(categorySet,categoryId);


                List<Integer> categoryIdList = Lists.newArrayList();
                if(categoryId != null){
                        for(Category categoryItem : categorySet){
                                categoryIdList.add(categoryItem.getId());
                        }
                }
                return ServerResponse.createBySuccess(categoryIdList);
        }


        //递归算法,算出子节点
        private Set<Category> findChildCategory(Set<Category> categorySet ,Integer categoryId){
                Category category = categoryMapper.selectByPrimaryKey(categoryId);
                if(category != null){
                        categorySet.add(category);
                }
                //查找子节点,递归算法一定要有一个退出的条件
                List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
                for(Category categoryItem : categoryList){
                        findChildCategory(categorySet,categoryItem.getId());
                }
                return categorySet;
        }
}
