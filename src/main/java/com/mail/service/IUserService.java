package com.mail.service;

import com.mail.common.ServerResponse;
import com.mail.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName IUserService.java
 * @Description TODO
 * @createTime 2018年11月21日 15:23:00
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld,String passwordNew, User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User>getInformation(Integer userId);

    //backend
    ServerResponse checkAdminRole(User user);

}
