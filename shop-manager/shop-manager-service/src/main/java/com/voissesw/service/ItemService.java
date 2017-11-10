package com.voissesw.service;

import com.voissesw.common.easyui.pojo.DataGridResult;
import com.voissesw.common.generic.GenericService;
import com.voissesw.pojo.TbItem;

/**
 * Created by YC on 2017/11/6.
 */
public interface ItemService extends GenericService<TbItem,Long>{

    DataGridResult selectItemList(int page, int rows);
}
