package com.kagarise.news_manager.utils;

import com.kagarise.news_manager.entity.NewsItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsItemUtil extends JpaRepository<NewsItemEntity, Integer> {
}
