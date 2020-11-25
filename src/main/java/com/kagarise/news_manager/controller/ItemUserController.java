package com.kagarise.news_manager.controller;

import com.kagarise.news_manager.entity.ItemUserEntity;
import com.kagarise.news_manager.utils.ItemUserUtil;
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
public class ItemUserController {
    private final ItemUserUtil itemUserUtil;

    public ItemUserController(ItemUserUtil dbUtils) {
        this.itemUserUtil = dbUtils;
    }

    @GetMapping("/itemUser/add")
    public Object addUserItemInfo(@Valid ItemUserEntity itemUserEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return "不符合条件，插入失败，请检查是不是有正确的请求参数";
        }
        itemUserUtil.save(itemUserEntity);
        return itemUserEntity;
    }

    @GetMapping("/itemUser/delete")
    public String deleteItemUserById(@RequestParam(value = "itemUserId") Integer id) {
        Optional<ItemUserEntity> itemUserEntity = itemUserUtil.findById(id);
        if (itemUserEntity.isPresent()) {
            ItemUserEntity itemUser = itemUserEntity.get();
            itemUserUtil.deleteById(id);
            return "{'msg':'删除成功','object':'" + itemUser.toString() + "'}";
        }
        return "没有办法删除：找不到要删除的信息！";
    }

    @GetMapping("/itemUser/update")
    public Object updateItemUserById(@RequestParam("itemUserId") Integer id,
                                     @RequestParam(value = "userId", required = false, defaultValue = "-1") Integer userId,
                                     @RequestParam(value = "itemId", required = false, defaultValue = "-1") Integer itemId) {
        Optional<ItemUserEntity> itemUserEntity = itemUserUtil.findById(id);
        if (itemUserEntity.isPresent()) {
            ItemUserEntity itemUser = itemUserEntity.get();
            if (userId != -1) {
                itemUser.setUserId(userId);
            }
            if (itemId != -1) {
                itemUser.setItemId(itemId);
            }
            itemUserUtil.save(itemUser);
            return itemUser;
        }
        return "修改失败，没有找到信息！";
    }

    @GetMapping("/itemUser")
    public Object getItemUserInfos() {
        List<ItemUserEntity> itemUserEntities = itemUserUtil.findAll();
        if (!itemUserEntities.isEmpty())
            return itemUserEntities;
        else {
            return "没有信息";
        }
    }

    @GetMapping("/itemUser/{itemUserId}")
    public Object getItemUserById(@PathVariable("itemUserId") Integer id) {
        Optional<ItemUserEntity> itemUserEntity = itemUserUtil.findById(id);
        if (itemUserEntity.isPresent())
            return itemUserEntity;
        else
            return "查找失败！";
    }
}
