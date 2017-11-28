package com.voissesw.rest.service;

import com.voissesw.common.pojo.TaotaoResult;

/**
 * Created by hasee on 2017/11/28.
 */
public interface RedisService {
    TaotaoResult contentCacheSync(Long contentId);
}
