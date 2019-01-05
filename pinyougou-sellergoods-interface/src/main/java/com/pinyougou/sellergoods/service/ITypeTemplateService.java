package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.TbTypeTemplate;
import com.pinyougou.page.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface ITypeTemplateService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<TbTypeTemplate> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult search(TbTypeTemplate typeTemplate, int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(TbTypeTemplate typeTemplate);


    /**
     * 修改
     */
    void update(TbTypeTemplate typeTemplate);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    TbTypeTemplate findOne(Long id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 获取规格选项列表
     *
     * @return
     */
    List<Map> selectTypeTemplateOptionList();
}
