package com.pinyougou.group;

import com.pinyougou.entity.TbSpecification;
import com.pinyougou.entity.TbSpecificationOption;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.group.SpecificationGroup
 * Date:         2018/10/6
 * Desc:         TbSpecification和List<TbSpecificationOption>的规格组合实体类
 */
@Data
public class SpecificationGroup implements Serializable {

    TbSpecification specification;

    List<TbSpecificationOption> specificationOptionList;
}
