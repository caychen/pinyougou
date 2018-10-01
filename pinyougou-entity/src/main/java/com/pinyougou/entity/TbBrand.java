package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbBrand implements Serializable {
    private Long id;

    private String name;

    private String firstChar;

}