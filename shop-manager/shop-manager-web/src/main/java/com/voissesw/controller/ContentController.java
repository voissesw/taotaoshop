package com.voissesw.controller;

import com.voissesw.common.easyui.pojo.EUDataGridResult;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbContent;
import com.voissesw.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by hasee on 2017/11/21.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult GetCantentByCategoryId(long categoryId, int page, int rows) {
        return contentService.selectContentByCategoryId(categoryId, page, rows);
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult SaveCantent(TbContent tbContent) {
        return contentService.inserTBContent(tbContent);
    }


}
