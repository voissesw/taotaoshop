package com.voissesw.service;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbItemParam;

/**
 * Created by hasee on 2017/11/16.
 */
public interface ItemParamService {
    TaotaoResult selectItemParamByCid(long cid);

    TaotaoResult insertItemParam(TbItemParam itemParam);
}
