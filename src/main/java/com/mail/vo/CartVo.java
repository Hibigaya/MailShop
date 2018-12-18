package com.mail.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName CartVo.java
 * @Description TODO
 * @createTime 2018年12月09日 16:52:00
 */
public class CartVo {
   private List<CartProductVo> cartProductVoList;
   private BigDecimal cartTotalPrice;
   private Boolean allChecked;
   private String imageHost;

    public List<CartProductVo> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<CartProductVo> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Boolean getAllChecked() {
        return allChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        this.allChecked = allChecked;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
