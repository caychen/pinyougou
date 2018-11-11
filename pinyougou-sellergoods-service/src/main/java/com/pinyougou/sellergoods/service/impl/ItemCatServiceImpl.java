package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.pinyougou.entity.TbGoods;
import com.pinyougou.entity.TbItemCat;
import com.pinyougou.entity.TbItemCatExample;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.IItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.sellergoods.service.impl.ItemCatServiceImpl
 * Date:         2018/11/3
 * Desc:
 */

@Service
@Component("itemCatService")
public class ItemCatServiceImpl implements IItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> findAll() {
        return tbItemCatMapper.selectByExample(null);
    }

    @Override
    public PageResult search(TbItemCat itemCat, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbItemCatExample example = null;
        if (itemCat != null) {
            TbItemCatExample.Criteria criteria = example.createCriteria();

            if (StringUtils.isNotBlank(itemCat.getName())) {
                criteria.andNameLike("%" + itemCat.getName() + "%");
            }
        }

        Page<TbItemCat> page = (Page<TbItemCat>) tbItemCatMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void add(TbItemCat itemCat) {
        tbItemCatMapper.insert(itemCat);
    }

    @Override
    @Transactional
    public void update(TbItemCat itemCat) {
        tbItemCatMapper.updateByPrimaryKey(itemCat);
    }

    @Override
    public TbItemCat findOne(Long id) {
        return tbItemCatMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        Lists.newArrayList(ids).stream().forEach(id -> tbItemCatMapper.deleteByPrimaryKey(id));
    }

    @Override
    public List<TbItemCat> findByParentId(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parentId);

        return tbItemCatMapper.selectByExample(example);
    }
}
