package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.pinyougou.entity.TbGoods;
import com.pinyougou.entity.TbGoodsExample;
import com.pinyougou.group.GoodsGroup;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.sellergoods.service.impl.GoodsServiceImpl
 * Date:         2018/10/27
 * Desc:
 */
@Service
@Component("goodsService")
@Slf4j
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;

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
        log.info("search方法查询到[{}]条记录", page.getTotal());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void add(GoodsGroup goodsGroup) {
        goodsGroup.getGoods().setAuditStatus("0");//状态，未审核
        tbGoodsMapper.insert(goodsGroup.getGoods());

        //获取goods的id
        goodsGroup.getGoodsDesc().setGoodsId(goodsGroup.getGoods().getId());
        tbGoodsDescMapper.insert(goodsGroup.getGoodsDesc());
    }

    @Override
    @Transactional
    public void update(TbGoods goods) {
        tbGoodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public TbGoods findOne(Long id) {
        return tbGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        Lists.newArrayList(ids).stream().forEach(id -> tbGoodsMapper.deleteByPrimaryKey(id));
    }
}
