package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.TbBrand;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(null);

        return new PageResult(page.getTotal(), page.getResult());
    }
}
