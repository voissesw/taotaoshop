package com.voissesw.portal.pojo;

import com.voissesw.pojo.TbItem;

/**
 * Created by hasee on 2017/12/4.
 */
public class ItemInfo extends TbItem {
    public String[] getImages() {
        return this.getImage().split(",");
    }
}
