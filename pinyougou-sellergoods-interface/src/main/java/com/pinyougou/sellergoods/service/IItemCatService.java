package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbGoods;
import com.pinyougou.entity.TbItemCat;
import com.pinyougou.page.PageResult;

import java.util.List;

/**
 * Author:       Caychen
 * Interface:    com.pinyougou.sellergoods.service.IItemCatService
 * Date:         2018/11/3
 * Desc:
 */

public interface IItemCatService {

    List<TbItemCat> findAll();

    PageResult search(TbItemCat itemCat, int page, int size);

    void add(TbItemCat itemCat);

    void update(TbItemCat itemCat);

    TbItemCat findOne(Long id);

    void delete(Long[] ids);

    /**
     * 根据上级id查找数据
     *
     * @param parentId
     * @return
     */
    List<TbItemCat> findByParentId(Long parentId);
}
