package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbGoods;
import com.pinyougou.group.GoodsGroup;
import com.pinyougou.page.PageResult;

import java.util.List;

/**
 * Author:       Caychen
 * Interface:    com.pinyougou.sellergoods.service.IGoodsService
 * Date:         2018/10/27
 * Desc:
 */

public interface IGoodsService {
    List<TbGoods> findAll();

    PageResult search(TbGoods goods, int page, int size);

    void add(GoodsGroup goodsGroup);

    void update(TbGoods goods);

    TbGoods findOne(Long id);

    void delete(Long[] ids);
}
