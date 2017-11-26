package com.voissesw.controller;

import com.voissesw.common.easyui.pojo.EUDataGridResult;
import com.voissesw.common.easyui.pojo.EUTreeNode;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbItem;
import com.voissesw.pojo.TbItemCat;
import com.voissesw.service.ItemCatService;
import com.voissesw.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public EUDataGridResult getItemList(int page, int rows) {
        EUDataGridResult result = itemService.selectItemList(page, rows);
        return result;
    }

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List getItemCat(@RequestParam(value="id", defaultValue="0") Long parentId) {
        List<EUTreeNode> catList = new ArrayList();
        //查询数据库
        List<TbItemCat> list = itemCatService.selectItemCatByParentId(parentId);
        for (TbItemCat tbItemCat : list) {
            EUTreeNode node = new EUTreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            //如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            catList.add(node);
        }
        return catList;
    }

    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertItem(TbItem item,String desc,String itemParams) {
        return itemService.insertItem(item,desc,itemParams);
    }



    @RequestMapping(value = "/item/query/item/desc/{id}")
    @ResponseBody
    public TaotaoResult getItemDesc(@PathVariable Long id) {
        return itemService.SelectItemDesc(id);
    }

    @RequestMapping(value = "/item/update", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateItem(TbItem item,String desc,String itemParams,Long itemParamId) {
        return itemService.updateItem(item,desc,itemParams,itemParamId);
    }

}
