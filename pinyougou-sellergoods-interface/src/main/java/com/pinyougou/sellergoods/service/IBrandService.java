package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbBrand;

import java.util.List;

/**
 * 品牌接口
 *
 */
public interface IBrandService {

    List<TbBrand> findAll();
}
