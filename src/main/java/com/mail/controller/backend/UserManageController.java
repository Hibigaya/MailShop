package com.mail.controller.backend;

import com.mail.common.Const;
import com.mail.common.ServerResponse;
import com.mail.pojo.User;
import com.mail.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName UserManageController.java
 * @Description TODO
 * @createTime 2018年11月23日 22:39:00
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {
    @Autowired
    private IUserService userService;

    /**
     * @description 管理员登陆
     * @author hibiki
     * @param
     * @updateTime 2018/11/23 22:40
     * @return
     * @throws
     */
    @RequestMapping(value="login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User>login(String username, String password, HttpSession session){
        ServerResponse<User> response=userService.login(username,password);
        if (response.isSuccess()){
            User user=response.getData();
            if(user.getRole()== Const.Role.ROLE_ADMIN){
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            }else{
                return ServerResponse.createByErrorMessage("不是管理员,无法登陆");
            }
        }
        return response;
    }
}
