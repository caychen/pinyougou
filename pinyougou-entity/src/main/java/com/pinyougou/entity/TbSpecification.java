package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 规格
 */
@Data
public class TbSpecification implements Serializable {
    private Long id;

    //规格名称
    private String specName;
}