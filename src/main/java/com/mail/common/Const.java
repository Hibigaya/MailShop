package com.mail.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName Const.java
 * @Description TODO
 * @createTime 2018年11月21日 20:36:00
 */
public class Const {
    public  static  final  String CURRENT_USER="current_user";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public  interface  Role{
        int ROLE_CUSTOMER=0;//普通用户
        int ROLE_ADMIN=1;//管理员
    }
    public interface ProductListOrderBy{
        Set<String>PRICE_ASC_DESC= Sets.newHashSet("price_desc","price_asc");
    }
    public interface Cart{
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL="LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS="LIMIT_NUM_SUCCESS";
    }
    public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        ProductStatusEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }
        public String getValue() {
            return value;
        }
        public int getCode() {
            return code;
        }
    }

}
