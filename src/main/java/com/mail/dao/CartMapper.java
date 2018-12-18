package com.mail.dao;

import com.mail.pojo.Cart;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdProductId(Integer userId,Integer productId);

    List<Cart>selectCartByUserId(Integer userId);

    int selectCartProductCheckedStatusByUserId(Integer userId);
}