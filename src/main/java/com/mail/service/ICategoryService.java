package com.mail.service;

import com.mail.common.ServerResponse;
import com.mail.pojo.Category;

import java.util.List;

/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName ICategoryService.java
 * @Description TODO
 * @createTime 2018年11月26日 17:27:00
 */
public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId );

    ServerResponse setCategoryName(Integer categoryId,String categoryName);

    ServerResponse<List<Category>>getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>>selectCategoryAndChildrenById(Integer categoryId);
}
