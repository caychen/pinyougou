package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.pinyougou.entity.TbSpecification;
import com.pinyougou.entity.TbSpecificationExample;
import com.pinyougou.entity.TbSpecificationOption;
import com.pinyougou.entity.TbSpecificationOptionExample;
import com.pinyougou.group.SpecificationGroup;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.ISpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service(timeout = 5000)
@Component("specificationService")
@Slf4j
public class SpecificationServiceImpl implements ISpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult search(TbSpecification specification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();

        if (specification != null) {
            if (StringUtils.isNotBlank(specification.getSpecName())) {
                criteria.andSpecNameLike("%" + specification.getSpecName() + "%");
            }
        }

        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        log.info("search方法查询到[{}]条记录", page.getTotal());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    @Transactional
    public void add(SpecificationGroup specificationGroup) {

        //获取规格实体
        TbSpecification tbSpecification = specificationGroup.getSpecification();
        specificationMapper.insert(tbSpecification);

        //获取规格选项集合
        List<TbSpecificationOption> specificationOptionList = specificationGroup.getSpecificationOptionList();
        specificationOptionList.stream().forEach(specificationOption -> {
            specificationOption.setSpecId(tbSpecification.getId());// 设置规格ID
            specificationOptionMapper.insert(specificationOption);// 新增规格
        });

    }


    /**
     * 修改
     */
    @Override
    @Transactional
    public void update(SpecificationGroup specificationGroup) {

        //获取规格实体
        TbSpecification tbSpecification = specificationGroup.getSpecification();
        specificationMapper.updateByPrimaryKey(tbSpecification);

        //删除原来规格对应的规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(tbSpecification.getId());
        specificationOptionMapper.deleteByExample(example);

        //获取规格选项集合
        List<TbSpecificationOption> specificationOptionList = specificationGroup.getSpecificationOptionList();
        specificationOptionList.stream().forEach(specificationOption -> {
            specificationOption.setSpecId(tbSpecification.getId());//设置规格ID
            specificationOptionMapper.insert(specificationOption);//新增规格
        });

    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public SpecificationGroup findOne(Long id) {

        SpecificationGroup specificationGroup = new SpecificationGroup();

        //获取规格实体
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        specificationGroup.setSpecification(tbSpecification);

        //获取规格选项列表
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> specificationOptionList = specificationOptionMapper.selectByExample(example);

        specificationGroup.setSpecificationOptionList(specificationOptionList);

        return specificationGroup;//组合实体类
    }

    /**
     * 批量删除
     */
    @Override
    @Transactional
    public void delete(Long[] ids) {
        Lists.newArrayList(ids).stream().forEach(id -> {
            //删除规格表数据
            specificationMapper.deleteByPrimaryKey(id);

            //删除规格选项表数据
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        });
    }


    @Override
    public List<Map> selectSpecificationOptionList() {
        // TODO Auto-generated method stub
        return specificationMapper.selectSpecificationOptionList();
    }

}
