package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbContent implements Serializable {
    private Long id;

    private Long categoryId;

    private String title;

    private String url;

    private String pic;

    private String status;

    private Integer sortOrder;


}