package com.pinyougou.group;

import com.pinyougou.entity.TbGoods;
import com.pinyougou.entity.TbGoodsDesc;
import com.pinyougou.entity.TbItem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.group.GoodsGroup
 * Date:         2018/12/1
 * Desc:
 */
@Data
public class GoodsGroup implements Serializable {

    private TbGoods goods;//商品基本信息SPU

    private TbGoodsDesc goodsDesc;//商品扩展信息SPU

    private List<TbItem> itemList;//商品SKU列表

}
