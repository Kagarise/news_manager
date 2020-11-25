package com.kagarise.news_manager.controller;

import com.kagarise.news_manager.entity.UserInfoEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.kagarise.news_manager.utils.UserInfoUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserInfoController {
    private final UserInfoUtil userInfoUtil;

    public UserInfoController(UserInfoUtil dbUtils) {
        this.userInfoUtil = dbUtils;
    }

    @GetMapping("/user/add")
    public Object addUserInfo(@Valid UserInfoEntity userInfoEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return "false";
        }
        userInfoUtil.save(userInfoEntity);
        return userInfoEntity;
    }

    @GetMapping("/user/delete")
    public Object deleteUserInfoById(@RequestParam(value = "userId") Integer id) {
        Optional<UserInfoEntity> userInfoEntity = userInfoUtil.findById(id);
        if (userInfoEntity.isPresent()) {
            UserInfoEntity user = userInfoEntity.get();
            userInfoUtil.deleteById(id);
            return user;
        }
        return "false";
    }

    @GetMapping("/user/update")
    public Object updateUserInfoById(@RequestParam("userId") Integer id,
                                     @RequestParam(value = "userName", required = false, defaultValue = "null") String name,
                                     @RequestParam(value = "userPwd", required = false, defaultValue = "null") String password) {
        Optional<UserInfoEntity> userInfoEntity = userInfoUtil.findById(id);
        if (userInfoEntity.isPresent()) {
            UserInfoEntity student = userInfoEntity.get();
            if (!name.equals("null")) {
                student.setUserName(name);
            }
            if (!password.equals("null")) {
                student.setUserPwd(password);
            }
            userInfoUtil.save(student);
            return student;
        }
        return "false";
    }

    @GetMapping("/user")
    public Object getUserInfos() {
        List<UserInfoEntity> userInfoEntities = userInfoUtil.findAll();
        if (!userInfoEntities.isEmpty())
            return userInfoEntities;
        else {
            return "false";
        }
    }

    @GetMapping("/user/{userId}")
    public Object getUserInfoById(@PathVariable("userId") Integer id) {
        Optional<UserInfoEntity> userInfoEntity = userInfoUtil.findById(id);
        if (userInfoEntity.isPresent())
            return userInfoEntity;
        else
            return "false";
    }
}
