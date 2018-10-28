package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbSeller;
import com.pinyougou.page.PageResult;

import java.util.List;

/**
 * Author:       Caychen
 * Interface:    com.pinyougou.sellergoods.service.ISellerService
 * Date:         2018/10/27
 * Desc:
 */

public interface ISellerService {
    List<TbSeller> findAll();

    PageResult search(TbSeller seller, int page, int size);

    void add(TbSeller seller);

    void update(TbSeller seller);

    TbSeller findOne(String id);

    void delete(String[] ids);

    void updateStatus(String sellerId, String status);
}
