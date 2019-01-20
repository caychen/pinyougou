package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.pinyougou.entity.*;
import com.pinyougou.group.GoodsGroup;
import com.pinyougou.mapper.*;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.sellergoods.service.impl.GoodsServiceImpl
 * Date:         2018/10/27
 * Desc:
 */
@Service(timeout = 5000)
@Component("goodsService")
@Slf4j
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Autowired
    private TbSellerMapper tbSellerMapper;

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

        if (StringUtils.equals(goodsGroup.getGoods().getIsEnableSpec(), "1")) {

            //规格选项
            goodsGroup.getItemList().stream().forEach(item -> {
                //标题
                String title = goodsGroup.getGoods().getGoodsName();
                Map<String, Object> map = JSON.parseObject(item.getSpec());
                title += " " + Joiner.on(" ").join(map.values());
                item.setTitle(title);
                setItemValues(goodsGroup, item);

                tbItemMapper.insert(item);
            });
        } else {

            TbItem item = new TbItem();
            item.setTitle(goodsGroup.getGoods().getGoodsName());
            item.setPrice(goodsGroup.getGoods().getPrice());
            item.setNum(9999);//默认库存
            item.setStatus("1");
            item.setIsDefault("1");
            item.setSpec("{}");

            setItemValues(goodsGroup, item);

            tbItemMapper.insert(item);
        }
    }

    private void setItemValues(GoodsGroup goodsGroup, TbItem item) {
        //分类id:三级分类
        item.setCategoryid(goodsGroup.getGoods().getCategory3Id());
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());

        //商品id
        item.setGoodsId(goodsGroup.getGoods().getId());

        //商家id
        item.setSellerId(goodsGroup.getGoods().getSellerId());

        //分类名称
        TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(goodsGroup.getGoods().getCategory3Id());
        item.setCategory(tbItemCat.getName());

        //品牌名称
        TbBrand tbBrand = tbBrandMapper.selectByPrimaryKey(goodsGroup.getGoods().getBrandId());
        item.setBrand(tbBrand.getName());

        //商家名称(店铺)
        TbSeller tbSeller = tbSellerMapper.selectByPrimaryKey(goodsGroup.getGoods().getSellerId());
        item.setSeller(tbSeller.getNickName());

        //图片
        List<Map> imageList = JSON.parseArray(goodsGroup.getGoodsDesc().getItemImages(), Map.class);
        if (imageList.size() > 0) {
            item.setImage((String) imageList.get(0).get("url"));
        }
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
