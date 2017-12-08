package com.voissesw.portal.controller;

import com.voissesw.portal.pojo.SearchResult;
import com.voissesw.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hasee on 2017/12/3.
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("/search")
    public String search(@RequestParam("q") String q, @RequestParam(defaultValue = "1") int page, Model model) {
        SearchResult searchResult = searchService.search(q, page);
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("q", q);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", searchResult.getPageCount());
        return "search";
    }
}
