package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.pinyougou.entity.TbBrand;
import com.pinyougou.entity.TbBrandExample;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.IBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Component("brandService")
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    @Override
    public PageResult search(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbBrandExample example = null;
        if (brand != null) {
            example = new TbBrandExample();
            TbBrandExample.Criteria criteria = example.createCriteria();

            if (StringUtils.isNotBlank(brand.getName())) {
                criteria.andNameLike("%" + brand.getName() + "%");
            }
            if (StringUtils.isNotBlank(brand.getFirstChar())) {
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(example);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void add(TbBrand brand) {
        tbBrandMapper.insert(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void update(TbBrand brand) {
        tbBrandMapper.updateByPrimaryKey(brand);
    }

    @Override
    @Transactional
    public void delete(Long[] idArray) {
        Lists.newArrayList(idArray).stream().forEach(id -> tbBrandMapper.deleteByPrimaryKey(id));
    }
}
