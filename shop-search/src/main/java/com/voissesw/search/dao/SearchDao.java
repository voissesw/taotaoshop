package com.voissesw.search.dao;

import com.voissesw.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

public interface SearchDao {
    SearchResult search(SolrQuery solrQuery) throws SolrServerException;
}
