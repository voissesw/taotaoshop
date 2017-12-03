package com.voissesw.search.dao.impl;

import com.voissesw.search.dao.SearchDao;
import com.voissesw.search.pojo.SearchItemPoJo;
import com.voissesw.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;


    @Override
    public SearchResult search(SolrQuery solrQuery) throws SolrServerException {
        SearchResult result = new SearchResult();
        //查询
        QueryResponse query = solrServer.query(solrQuery);
        //结果集
        List<SearchItemPoJo> beans = query.getBeans(SearchItemPoJo.class);
        result.setRecorcCount((int) query.getResults().getNumFound());
        //是否高亮
        if (solrQuery.getHighlight()) {
            //取高亮的域
            String[] highlightFields = solrQuery.getHighlightFields();
            //取高亮结果集
            Map<String, Map<String, List<String>>> beansHighlighting = query.getHighlighting();
            for (SearchItemPoJo bean : beans) {
                Map<String, List<String>> beanHighlighting = beansHighlighting.get(bean.getId());
                for (String highlightField : highlightFields) {
                    List<String> highlightFieldValues = beanHighlighting.get(highlightField);
                    if (highlightFieldValues != null) {
                        String highlightFieldValue = highlightFieldValues.get(0);
                        bean.setItem_title(highlightFieldValue);
                    }
                }
            }
        }

        result.setItemList(beans);
        return result;
    }
}
