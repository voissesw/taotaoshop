package com.voissesw.rest.controller;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbContent;
import com.voissesw.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
@RequestMapping("content")
public class ContnetController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public Object getitamcat(@PathVariable Long contentCategoryId) {

        try {
            List<TbContent> contents = contentService.selectContentList(contentCategoryId);
            return TaotaoResult.ok(contents);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,e.getMessage());
        }

    }


}
