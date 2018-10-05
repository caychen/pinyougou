package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbItemCat;
import com.pinyougou.page.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface IItemCatService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<TbItemCat> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult search(TbItemCat itemCat, int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(TbItemCat itemCat);


    /**
     * 修改
     */
    void update(TbItemCat itemCat);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    TbItemCat findOne(Long id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long[] ids);

}
