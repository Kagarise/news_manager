package com.kagarise.news_manager.utils;

import com.kagarise.news_manager.entity.ItemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemUserUtil extends JpaRepository<ItemUserEntity, Integer> {
}
