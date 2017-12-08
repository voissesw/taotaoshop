package com.voissesw.rest.service;

import com.voissesw.common.pojo.TaotaoResult;

/**
 * Created by hasee on 2017/12/4.
 */
public interface ItemService {
    public TaotaoResult getItemBaseInfo(Long id);

    TaotaoResult getItemDesc(Long id);

    TaotaoResult getItemParam(Long id);
}
