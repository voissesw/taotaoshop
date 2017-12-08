package com.voissesw.portal.service;

import com.voissesw.pojo.TbItem;
import com.voissesw.pojo.TbItemDesc;
import com.voissesw.pojo.TbItemParamItem;
import com.voissesw.portal.pojo.ItemInfo;

/**
 * Created by hasee on 2017/12/4.
 */
public interface ItemService {
    ItemInfo getItemBaseInfo(long id);

    TbItemDesc getItemDesc(long id);

    TbItemParamItem getItemParam(long id);
}
