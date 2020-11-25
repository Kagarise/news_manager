package com.kagarise.news_manager.utils;

import com.kagarise.news_manager.entity.NewsInfoEntity;
import com.kagarise.news_manager.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsInfoUtil extends JpaRepository<NewsInfoEntity, Integer> {
    List<NewsInfoEntity> findByItemId(Integer itemId);
}
