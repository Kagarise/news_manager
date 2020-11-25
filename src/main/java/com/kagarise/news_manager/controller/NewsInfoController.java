package com.kagarise.news_manager.controller;

import com.kagarise.news_manager.entity.NewsInfoEntity;
import com.kagarise.news_manager.entity.UserInfoEntity;
import com.kagarise.news_manager.utils.NewsInfoUtil;
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
public class NewsInfoController {
    private final NewsInfoUtil newsInfoUtil;

    public NewsInfoController(NewsInfoUtil dbUtils) {
        this.newsInfoUtil = dbUtils;
    }

    @GetMapping("/news/add")
    public Object addNewsInfo(@Valid NewsInfoEntity newsInfoEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return "false";
        }
        newsInfoUtil.save(newsInfoEntity);
        return newsInfoEntity;
    }

    @GetMapping("/news/delete")
    public Object deleteNewsInfoById(@RequestParam(value = "newsId") Integer id) {
        Optional<NewsInfoEntity> newsInfoEntity = newsInfoUtil.findById(id);
        if (newsInfoEntity.isPresent()) {
            NewsInfoEntity news = newsInfoEntity.get();
            newsInfoUtil.deleteById(id);
            return news;
        }
        return "false";
    }

    @GetMapping("/news/update")
    public Object updateNewsInfoById(@RequestParam("newsId") Integer id,
                                     @RequestParam(value = "itemId", required = false, defaultValue = "-1") Integer itemId,
                                     @RequestParam(value = "newsTitle", required = false, defaultValue = "null") String newsTitle,
                                     @RequestParam(value = "newsImage", required = false, defaultValue = "null") String newsImage,
                                     @RequestParam(value = "newsContent", required = false, defaultValue = "null") String newsContent) {
        Optional<NewsInfoEntity> newsInfoEntity = newsInfoUtil.findById(id);
        if (newsInfoEntity.isPresent()) {
            NewsInfoEntity news = newsInfoEntity.get();
            if (itemId != -1) {
                news.setItemId(itemId);
            }
            if (!newsTitle.equals("null")) {
                news.setNewsTitle(newsTitle);
            }
            if (!newsImage.equals("null")) {
                news.setNewsImage(newsImage);
            }
            if (!newsContent.equals("null")) {
                news.setNewsContent(newsContent);
            }
            newsInfoUtil.save(news);
            return news;
        }
        return "false";
    }

    @GetMapping("/news")
    public Object getNewsInfos() {
        List<NewsInfoEntity> newsInfoEntities = newsInfoUtil.findAll();
        if (!newsInfoEntities.isEmpty())
            return newsInfoEntities;
        else {
            return "false";
        }
    }

    @GetMapping("/news/{newsId}")
    public Object getNewsInfoById(@PathVariable("newsId") Integer id) {
        Optional<NewsInfoEntity> newsInfoEntity = newsInfoUtil.findById(id);
        if (newsInfoEntity.isPresent())
            return newsInfoEntity;
        else
            return "false";
    }

    @GetMapping("/news/item/{itemId}")
    public Object getNewsInfoByItemId(@PathVariable("itemId") Integer id) {
        List<NewsInfoEntity> newsInfoEntity = newsInfoUtil.findByItemId(id);
        if (!newsInfoEntity.isEmpty())
            return newsInfoEntity;
        else
            return "false";
    }
}
