package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.pinyougou.entity.TbGoods;
import com.pinyougou.entity.TbGoodsExample;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.IGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.sellergoods.service.impl.GoodsServiceImpl
 * Date:         2018/10/27
 * Desc:
 */
@Service
@Component("goodsService")
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Override
    public List<TbGoods> findAll() {
        return tbGoodsMapper.selectByExample(null);
    }

    @Override
    public PageResult search(TbGoods goods, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbGoodsExample example = null;
        if (goods != null) {
            TbGoodsExample.Criteria criteria = example.createCriteria();

            if (StringUtils.isNotBlank(goods.getSellerId())) {
                criteria.andSellerIdLike("%" + goods.getSellerId() + "%");
            }
            if (StringUtils.isNotBlank(goods.getGoodsName())) {
                criteria.andGoodsNameLike("%" + goods.getGoodsName() + "%");
            }
            if (StringUtils.isNotBlank(goods.getAuditStatus())) {
                criteria.andAuditStatusLike("%" + goods.getAuditStatus() + "%");
            }
            if (StringUtils.isNotBlank(goods.getIsMarketable())) {
                criteria.andIsMarketableLike("%" + goods.getIsMarketable() + "%");
            }
            if (StringUtils.isNotBlank(goods.getCaption())) {
                criteria.andCaptionLike("%" + goods.getCaption() + "%");
            }
            if (StringUtils.isNotBlank(goods.getSmallPic())) {
                criteria.andSmallPicLike("%" + goods.getSmallPic() + "%");
            }
            if (StringUtils.isNotBlank(goods.getIsEnableSpec())) {
                criteria.andIsEnableSpecLike("%" + goods.getIsEnableSpec() + "%");
            }
            if (StringUtils.isNotBlank(goods.getIsDelete())) {
                criteria.andIsDeleteLike("%" + goods.getIsDelete() + "%");
            }

        }
        Page<TbGoods> page = (Page<TbGoods>) tbGoodsMapper.selectByExample(example);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbGoods goods) {
        tbGoodsMapper.insert(goods);
    }

    @Override
    public void update(TbGoods goods) {
        tbGoodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public TbGoods findOne(Long id) {
        return tbGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        Lists.newArrayList(ids).stream().forEach(id -> tbGoodsMapper.deleteByPrimaryKey(id));
    }
}
