package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbBrand;
import com.pinyougou.page.PageResult;

import java.util.List;

/**
 * 品牌接口
 *
 */
public interface IBrandService {

    List<TbBrand> findAll();

    /**
     * 品牌分页
     *
     * @param pageNum   当前页
     * @param pageSize  每页记录数
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);
}
