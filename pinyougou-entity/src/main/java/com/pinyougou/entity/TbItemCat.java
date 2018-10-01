package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbItemCat implements Serializable {
    private Long id;

    private Long parentId;

    private String name;

    private Long typeId;


}