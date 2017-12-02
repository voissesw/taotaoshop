package com.voissesw.search.controller;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hasee on 2017/12/2.
 */
@Controller
public class SearchController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/manager/importAll")
    @ResponseBody
    public TaotaoResult importAll() {
        return itemService.importAllItems();
    }

//    @RequestMapping("/query")
//    @ResponseBody
//    public TaotaoResult Search(String q, int page, int rows) {
//        return itemService.Search();
//    }
}
