package com.voissesw.service;

import com.voissesw.common.easyui.pojo.EUDataGridResult;
import com.voissesw.common.generic.GenericService;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbContent;

public interface ContentService extends GenericService<TbContent,Long>{

    EUDataGridResult selectContentByCategoryId(long categoryId, int page, int rows);

    TaotaoResult inserTBContent(TbContent tbContent);

    TaotaoResult deleteByIds(Long[] ids);

    TaotaoResult updateTBContent(TbContent tbContent);
}
