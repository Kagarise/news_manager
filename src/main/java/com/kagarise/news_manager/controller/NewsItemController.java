package com.kagarise.news_manager.controller;

import com.kagarise.news_manager.entity.NewsItemEntity;
import com.kagarise.news_manager.entity.UserInfoEntity;
import com.kagarise.news_manager.utils.NewsItemUtil;
import com.kagarise.news_manager.utils.UserInfoUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class NewsItemController {
    private final NewsItemUtil newsItemUtil;

    public NewsItemController(NewsItemUtil dbUtils) {
        this.newsItemUtil = dbUtils;
    }

    @GetMapping("/newsItem/add")
    public Object addNewsItemInfo(@Valid NewsItemEntity newsItemEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return "false";
        }
        newsItemUtil.save(newsItemEntity);
        return newsItemEntity;
    }

    @GetMapping("/newsItem/delete")
    public Object deleteNewsItemInfoById(@RequestParam(value = "itemId") Integer id) {
        Optional<NewsItemEntity> newsItemEntity = newsItemUtil.findById(id);
        if (newsItemEntity.isPresent()) {
            NewsItemEntity newsItem = newsItemEntity.get();
            newsItemUtil.deleteById(id);
            return newsItem;
        }
        return "false";
    }

    @GetMapping("/newsItem/update")
    public Object updateNewsItemInfoById(@RequestParam("itemId") Integer id,
                                     @RequestParam(value = "itemName", required = false, defaultValue = "null") String itemName) {
        Optional<NewsItemEntity> newsItemEntity = newsItemUtil.findById(id);
        if (newsItemEntity.isPresent()) {
            NewsItemEntity newsItem = newsItemEntity.get();
            if (!itemName.equals("null")) {
                newsItem.setItemName(itemName);
            }
            newsItemUtil.save(newsItem);
            return newsItem;
        }
        return "false";
    }

    @GetMapping("/newsItem")
    public Object getNewsItemInfos() {
        List<NewsItemEntity> newsItemEntities = newsItemUtil.findAll();
        if (!newsItemEntities.isEmpty())
            return newsItemEntities;
        else {
            return "false";
        }
    }

    @GetMapping("/newsItem/{itemId}")
    public Object getNewsItemInfoById(@PathVariable("itemId") Integer id) {
        Optional<NewsItemEntity> newsItemEntity = newsItemUtil.findById(id);
        if (newsItemEntity.isPresent())
            return newsItemEntity;
        else
            return "false";
    }
}
