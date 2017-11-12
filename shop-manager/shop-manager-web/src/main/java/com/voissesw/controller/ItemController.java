package com.voissesw.controller;

import com.voissesw.common.easyui.pojo.DataGridResult;
import com.voissesw.pojo.TbItem;
import com.voissesw.pojo.TbItemCat;
import com.voissesw.service.ItemCatService;
import com.voissesw.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem item = itemService.selectById(itemId);
        return item;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public DataGridResult getItemList(int page, int rows) {
        DataGridResult result = itemService.selectItemList(page, rows);
        return result;
    }

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List getItemCat(@RequestParam(value="id", defaultValue="0") Long parentId) {
        List catList = new ArrayList();
        //查询数据库
        List<TbItemCat> list = itemCatService.selectItemCatByParentId(parentId);
        for (TbItemCat tbItemCat : list) {
            Map node = new HashMap<>();
            node.put("id", tbItemCat.getId());
            node.put("text", tbItemCat.getName());
            //如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
            node.put("state", tbItemCat.getIsParent()?"closed":"open");
            catList.add(node);
        }
        return catList;

    }


}
