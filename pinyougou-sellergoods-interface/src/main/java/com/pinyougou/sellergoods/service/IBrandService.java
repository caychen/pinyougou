package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbBrand;
import com.pinyougou.page.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 品牌接口
 */
public interface IBrandService {

    /**
     * 查找所有品牌分类
     *
     * @return
     */
    List<TbBrand> findAll();

    /**
     * 根据条件查询品牌并分页
     *
     * @param brand    查询条件
     * @param pageNum  当前页
     * @param pageSize 每页记录数
     * @return
     */
    PageResult search(TbBrand brand, int pageNum, int pageSize);

    /**
     * 增加品牌
     *
     * @param brand
     */
    void add(TbBrand brand);

    /**
     * 根据id查找品牌
     *
     * @param id
     * @return
     */
    TbBrand findOne(Long id);

    /**
     * 更新品牌
     *
     * @param brand
     */
    void update(TbBrand brand);

    /**
     * 批量删除品牌
     *
     * @param ids
     */
    void delete(Long[] ids);

    List<Map> selectBrandOptionList();
}
