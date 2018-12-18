package com.mail.controller.portal;

import com.mail.common.Const;
import com.mail.common.ResponseCode;
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
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2018年11月21日 15:06:00
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * @param username password,session
     * @return null
     * @throws
     * @description 用户登录
     * @author hibiki
     * @updateTime 2018/11/21 15:15
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = userService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * @param
     * @return
     * @throws
     * @description 用户登出
     * @author hibiki
     * @updateTime 2018/11/21 20:43
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * @param
     * @return
     * @throws
     * @description 用户注册
     * @author hibiki
     * @updateTime 2018/11/21 20:54
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return userService.register(user);
    }

    /**
     * @param
     * @return
     * @throws
     * @description 校验
     * @author hibiki
     * @updateTime 2018/11/23 17:59
     */
    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return userService.checkValid(str, type);
    }

    /**
     * @param
     * @return
     * @throws
     * @description获取各户信息类
     * @author hibiki
     * @updateTime 2018/11/23 20:05
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }

    /**
     * @param
     * @return
     * @throws
     * @description 用户忘记密码问题
     * @author hibiki
     * @updateTime 2018/11/23 20:15
     */
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return userService.selectQuestion(username);
    }

    /**
     * @param
     * @return
     * @throws
     * @description 忘记密码问题
     * @author hibiki
     * @updateTime 2018/11/23 20:30
     */
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return userService.checkAnswer(username, question, answer);
    }

    /**
     * @param
     * @return
     * @throws
     * @description 重置密码
     * @author hibiki
     * @updateTime 2018/11/23 20:54
     */
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return userService.forgetRestPassword(username, passwordNew, forgetToken);
    }

    /**
     * @param
     * @return
     * @throws
     * @description 重置密码
     * @author hibiki
     * @updateTime 2018/11/23 21:19
     */
    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return userService.resetPassword(passwordOld, passwordNew, user);
    }
    /**
     * @param
     * @return
     * @throws
     * @description 更改个人信息
     * @author hibiki
     * @updateTime 2018/11/23 21:44
     */
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update_information(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = userService.updateInformation(user);
        if (response.isSuccess()) {
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }
    /**
     * @description
     * @author hibiki
     * @param
     * @updateTime 2018/11/23 22:31
     * @return
     * @throws
     */
    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User>get_information(HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if(currentUser==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
        }
        return userService.getInformation(currentUser.getId());

    }

}
