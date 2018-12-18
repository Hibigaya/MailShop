package com.mail.controller.portal;

import com.mail.common.Const;
import com.mail.common.ResponseCode;
import com.mail.common.ServerResponse;
import com.mail.pojo.User;
import com.mail.vo.CartVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName CartController.java
 * @Description TODO
 * @createTime 2018年12月09日 16:39:00
 */
@Controller
@RequestMapping("/cart/")
public class CartController {

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<CartVo> list(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return null;
    }
}
