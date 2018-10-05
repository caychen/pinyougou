package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbSeller;
import com.pinyougou.page.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface ISellerService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<TbSeller> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult search(TbSeller seller, int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(TbSeller seller);


    /**
     * 修改
     */
    void update(TbSeller seller);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    TbSeller findOne(String id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(String[] ids);

}
