package com.voissesw.search.service;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * Created by hasee on 2017/12/2.
 */
public interface ItemService {
    TaotaoResult importAllItems();

    SearchResult search(String q, int page, int rows) throws SolrServerException;
}
