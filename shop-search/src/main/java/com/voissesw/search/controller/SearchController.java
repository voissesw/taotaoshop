package com.voissesw.search.controller;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.search.pojo.SearchResult;
import com.voissesw.search.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/query")
    @ResponseBody
    public TaotaoResult Search(String q,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "60") int rows) {
        if (StringUtils.isBlank(q)) {
            return TaotaoResult.build(400, "查询条件不能为空");
        }
        try {
            SearchResult result = itemService.search(q, page, rows);
            return TaotaoResult.ok(result);
        } catch (SolrServerException e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }
    }
}
