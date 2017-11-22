package com.voissesw.controller;

import com.voissesw.common.easyui.pojo.EUTreeNode;
import com.voissesw.common.generic.GenericService;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbContentCategory;
import com.voissesw.pojo.TbItemCat;
import com.voissesw.service.impl.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2017/11/21.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController  {
    @Autowired
    ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List getItemCat(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EUTreeNode> catList = new ArrayList();
        //查询数据库
        List<TbContentCategory> list = contentCategoryService.selectContentCategoryByParentId(parentId);
        for (TbContentCategory TbContentCategory : list) {
            EUTreeNode node = new EUTreeNode();
            node.setId(TbContentCategory.getId());
            node.setText(TbContentCategory.getName());
            //如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
            node.setState(TbContentCategory.getIsParent() ? "closed" : "open");
            catList.add(node);
        }
        return catList;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createCantentCategory(long parentId, String name) {
        return contentCategoryService.insertContentCategory(parentId,name);
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateCantentCategory(int id, String name) {
        return TaotaoResult.ok(null);
    }

}
