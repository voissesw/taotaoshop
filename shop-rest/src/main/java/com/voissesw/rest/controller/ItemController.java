package com.voissesw.rest.controller;

import com.voissesw.common.easyui.pojo.DataGridResult;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbItem;
import com.voissesw.pojo.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
public class ItemController {


    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        return null;
    }


}
