package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbTypeTemplate implements Serializable {
    private Long id;

    private String name;

    private String specIds;

    private String brandIds;

    private String customAttributeItems;


}