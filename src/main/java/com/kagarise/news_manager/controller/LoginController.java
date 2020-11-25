package com.kagarise.news_manager.controller;

import com.kagarise.news_manager.entity.UserInfoEntity;
import com.kagarise.news_manager.utils.UserInfoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    private final UserInfoUtil userInfoUtil;

    public LoginController(UserInfoUtil dbUtils) {
        this.userInfoUtil = dbUtils;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String judgeLogin(@RequestParam("userName") String userName,
                             @RequestParam("userPwd") String userPwd) {
        List<UserInfoEntity> userInfoEntity = userInfoUtil.findByUserNameAndUserPwd(userName, userPwd);
        if (!userInfoEntity.isEmpty()){
            return "redirect:/index.html";
        }
        else{
            return "redirect:/login.html";
        }
    }
}
