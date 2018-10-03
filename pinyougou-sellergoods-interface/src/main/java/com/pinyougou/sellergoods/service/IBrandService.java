package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbBrand;
import com.pinyougou.page.PageResult;

import java.util.List;

/**
 * 品牌接口
 *
 */
public interface IBrandService {

    /**
     * 查找所有品牌分类
     *
     * @return
     */
    List<TbBrand> findAll();

    /**
     * 品牌分页
     *
     * @param pageNum   当前页
     * @param pageSize  每页记录数
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);

    /**
     * 增加品牌
     *
     * @param brand
     */
    void add(TbBrand brand);
}
