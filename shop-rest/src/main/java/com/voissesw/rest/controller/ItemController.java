package com.voissesw.rest.controller;

import com.voissesw.common.json.JsonUtils;
import com.voissesw.rest.pojo.CatResult;
import com.voissesw.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemCatService itemCatService;
//
//    @RequestMapping(value = "itemcat/all", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
//    @ResponseBody
//    public String getitamcat(String callback) {
//        CatResult itemCatNode = itemCatService.getItemCatNode();
//        String s = JsonUtils.objectToJson(itemCatNode);
//        return callback + "(" + s + ");";
//    }

    @RequestMapping("itemcat/all")
    @ResponseBody
    public Object getitamcat(String callback) {
        CatResult itemCatNode = itemCatService.getItemCatNode();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(itemCatNode);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }


}
