package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbGoods;
import com.pinyougou.page.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface IGoodsService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<TbGoods> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult search(TbGoods goods, int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(TbGoods goods);


    /**
     * 修改
     */
    void update(TbGoods goods);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    TbGoods findOne(Long id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long[] ids);

}
