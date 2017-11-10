package com.voissesw.controller;

import com.voissesw.common.easyui.pojo.DataGridResult;
import com.voissesw.pojo.TbItem;
import com.voissesw.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem item = itemService.selectById(itemId);
        return item;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public DataGridResult getItemById(int page, int rows) {
        DataGridResult result = itemService.selectItemList(page, rows);
        return result;
    }
}
