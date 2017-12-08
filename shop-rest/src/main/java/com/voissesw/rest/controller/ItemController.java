package com.voissesw.rest.controller;

import com.voissesw.common.json.JsonUtils;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.rest.pojo.CatResult;
import com.voissesw.rest.service.ItemCatService;
import com.voissesw.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemCatService itemCatService;

    @Autowired
    private ItemService itemService;

//
//    @RequestMapping(value = "itemcat/all", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
//    @ResponseBody
//    public String getitamcat(String callback) {
//        CatResult itemCatNode = itemCatService.getItemCatNode();
//        String s = JsonUtils.objectToJson(itemCatNode);
//        return callback + "(" + s + ");";
//    }

    @RequestMapping("/itemcat/all")
    @ResponseBody
    public Object getItamCat(String callback) {
        CatResult itemCatNode = itemCatService.getItemCatNode();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(itemCatNode);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

    @RequestMapping("/item/info/{id}")
    @ResponseBody
    public TaotaoResult getItamBaseInfo(@PathVariable Long id) {
        TaotaoResult result = itemService.getItemBaseInfo(id);
        return result;
    }

    @RequestMapping("/item/desc/{id}")
    @ResponseBody
    public TaotaoResult getItamDescInfo(@PathVariable Long id) {
        TaotaoResult result = itemService.getItemDesc(id);
        return result;
    }

    @RequestMapping("/item/param/{id}")
    @ResponseBody
    public TaotaoResult getItamParamInfo(@PathVariable Long id) {
        TaotaoResult result = itemService.getItemParam(id);
        return result;
    }


}
