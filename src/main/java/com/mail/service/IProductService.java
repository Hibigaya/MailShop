package com.mail.service;

import com.github.pagehelper.PageInfo;
import com.mail.common.ServerResponse;
import com.mail.pojo.Product;
import com.mail.vo.ProductDetailVo;
import org.springframework.stereotype.Service;

/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName IProductService.java
 * @Description TODO
 * @createTime 2018年11月28日 17:09:00
 */
public interface IProductService{
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String>setSaleStatus(Integer productId,Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(Integer pageNum, Integer pageSize);

    ServerResponse<PageInfo>searchProduct(Integer pageNum,Integer pageSize,String productName,Integer productId);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo>getProductByKeywordCategory(String keyword,Integer categoryId,Integer pageNum,Integer pageSize,String orderBy );
}
