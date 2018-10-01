package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbSpecificationOption implements Serializable {
    private Long id;

    private String optionName;

    private Long specId;

    private Integer orders;
}