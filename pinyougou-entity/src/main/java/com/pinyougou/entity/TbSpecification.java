package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbSpecification implements Serializable {
    private Long id;

    private String specName;
}