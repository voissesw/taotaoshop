package com.voissesw.portal.controller;

import com.voissesw.pojo.TbItem;
import com.voissesw.pojo.TbItemDesc;
import com.voissesw.pojo.TbItemParamItem;
import com.voissesw.portal.pojo.ItemInfo;
import com.voissesw.portal.service.ContentService;
import com.voissesw.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{id}")
    public String showIndex(@PathVariable long id, Model model) {
        ItemInfo item = itemService.getItemBaseInfo(id);
        model.addAttribute("item", item);
        TbItemDesc itemDesc = itemService.getItemDesc(id);
        model.addAttribute("itemDesc", itemDesc);
        TbItemParamItem itemParam = itemService.getItemParam(id);
        model.addAttribute("itemParam", itemParam.getParamData());
        return "item";
    }


}
