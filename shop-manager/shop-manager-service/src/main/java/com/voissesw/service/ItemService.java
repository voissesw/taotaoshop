package com.voissesw.service;

import com.voissesw.common.easyui.pojo.EUDataGridResult;
import com.voissesw.common.generic.GenericService;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbItem;

/**
 * Created by YC on 2017/11/6.
 */
public interface ItemService extends GenericService<TbItem,Long> {

    EUDataGridResult selectItemList(int page, int rows);

    TaotaoResult insertItem(TbItem item, String itemDesc, String itemParam);


    TaotaoResult updateItem(TbItem item, String itemDesc, String itemParam, Long itemParamId);

    TaotaoResult SelectItemDesc(Long ItemId);
}
