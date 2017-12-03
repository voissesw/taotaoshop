package com.voissesw.search.service.impl;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.search.dao.SearchDao;
import com.voissesw.search.mapper.SearchMapper;
import com.voissesw.search.pojo.SearchItemPoJo;
import com.voissesw.search.pojo.SearchResult;
import com.voissesw.search.service.ItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hasee on 2017/12/2.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchDao searchDao;
    @Override
    public TaotaoResult importAllItems(){
        List<SearchItemPoJo> items = searchMapper.getItemList();
        try {
            solrServer.addBeans(items);
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, "更新索引失败");
        }
        return TaotaoResult.ok();
    }

    @Override
    public SearchResult search(String q, int page, int rows) throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(q);
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        solrQuery.set("df", "item_keywords");
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        SearchResult result = searchDao.search(solrQuery);
        int recorcCount = result.getRecorcCount();
        int  pageCount = recorcCount %rows>0?(recorcCount / rows)+1:recorcCount / rows;
        result.setPageCount(pageCount);
        result.setCurPage(page);
        return result;

    }
}
