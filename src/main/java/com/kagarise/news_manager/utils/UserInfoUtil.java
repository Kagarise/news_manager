package com.kagarise.news_manager.utils;

import com.kagarise.news_manager.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoUtil extends JpaRepository<UserInfoEntity, Integer> {
    List<UserInfoEntity> findByUserNameAndUserPwd(String userName, String userPwd);
}
