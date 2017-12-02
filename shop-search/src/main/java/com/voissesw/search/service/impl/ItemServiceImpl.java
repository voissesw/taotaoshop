package com.voissesw.search.service.impl;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.search.mapper.SearchMapper;
import com.voissesw.search.pojo.SearchItemPoJo;
import com.voissesw.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
}
