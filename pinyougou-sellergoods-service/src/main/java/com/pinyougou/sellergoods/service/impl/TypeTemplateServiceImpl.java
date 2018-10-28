package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.pinyougou.entity.TbTypeTemplate;
import com.pinyougou.entity.TbTypeTemplateExample;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.ITypeTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Component("typeTemplateService")
public class TypeTemplateServiceImpl implements ITypeTemplateService {

    @Autowired
    private TbTypeTemplateMapper typeTemplateMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbTypeTemplate> findAll() {
        return typeTemplateMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult search(TbTypeTemplate typeTemplate, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();

        if (typeTemplate != null) {
            if (StringUtils.isNotBlank(typeTemplate.getName())) {
                criteria.andNameLike("%" + typeTemplate.getName() + "%");
            }

            if (StringUtils.isNotBlank(typeTemplate.getSpecIds())) {
                criteria.andSpecIdsLike("%" + typeTemplate.getSpecIds() + "%");
            }

            if (StringUtils.isNotBlank(typeTemplate.getBrandIds())) {
                criteria.andBrandIdsLike("%" + typeTemplate.getBrandIds() + "%");
            }

            if (StringUtils.isNotBlank(typeTemplate.getCustomAttributeItems())) {
                criteria.andCustomAttributeItemsLike("%" + typeTemplate.getCustomAttributeItems() + "%");
            }

        }

        Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) typeTemplateMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    @Transactional
    public void add(TbTypeTemplate typeTemplate) {
        typeTemplateMapper.insert(typeTemplate);
    }


    /**
     * 修改
     */
    @Override
    @Transactional
    public void update(TbTypeTemplate typeTemplate) {
        typeTemplateMapper.updateByPrimaryKey(typeTemplate);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbTypeTemplate findOne(Long id) {
        return typeTemplateMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    @Transactional
    public void delete(Long[] ids) {
        Lists.newArrayList(ids).forEach(id -> typeTemplateMapper.deleteByPrimaryKey(id));
    }

}
