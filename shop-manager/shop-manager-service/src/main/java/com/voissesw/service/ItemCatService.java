package com.voissesw.service;

import com.voissesw.common.generic.GenericService;
import com.voissesw.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService extends GenericService<TbItemCat,Long> {
    List<TbItemCat> selectItemCatByParentId(Long parentId);
}
