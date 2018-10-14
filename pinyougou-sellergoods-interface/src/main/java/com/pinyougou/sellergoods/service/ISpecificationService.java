package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbSpecification;
import com.pinyougou.group.SpecificationGroup;
import com.pinyougou.page.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface ISpecificationService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<TbSpecification> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult search(TbSpecification specification, int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(SpecificationGroup specificationGroup);


    /**
     * 修改
     */
    void update(SpecificationGroup specificationGroup);


    /**
     * 根据ID获取组合实体
     *
     * @param id
     * @return
     */
    SpecificationGroup findOne(Long id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long[] ids);


    /**
     * 返回规格下拉列表，格式： [data:[{id:'', text:''},{id:'',text:''}]]
     *
     * @return
     */
    List<Map> selectSpecificationOptionList();

}
