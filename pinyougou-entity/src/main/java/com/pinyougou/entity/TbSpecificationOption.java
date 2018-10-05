package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 规格选项
 *
 */
@Data
public class TbSpecificationOption implements Serializable {
    private Long id;

    //规格项名称
    private String optionName;

    //规格ID
    private Long specId;

    //排序值
    private Integer orders;
}