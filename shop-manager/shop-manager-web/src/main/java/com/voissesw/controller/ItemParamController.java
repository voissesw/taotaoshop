package com.voissesw.controller;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbItemParam;
import com.voissesw.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hasee on 2017/11/16.
 */
@RequestMapping("/item/param")
@Controller
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamList(@PathVariable Long itemCatId) {
        return itemParamService.selectItemParamByCid(itemCatId);
    }

    @RequestMapping(value = "/save/{itemCatId}", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long itemCatId, String paramData) {
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(itemCatId);
        itemParam.setParamData(paramData);
        return itemParamService.insertItemParam(itemParam);
    }

}
