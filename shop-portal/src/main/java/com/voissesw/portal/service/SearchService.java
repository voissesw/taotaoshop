package com.voissesw.portal.service;

import com.voissesw.portal.pojo.SearchResult;

/**
 * Created by hasee on 2017/12/3.
 */
public interface SearchService {
    SearchResult search(String q, int page);
}
