package com.kagarise.news_manager.utils;

import com.kagarise.news_manager.entity.LogsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsInfoUtil extends JpaRepository<LogsInfoEntity, Integer> {
}
