package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 内容分类
 */
@Data
public class TbContentCategory implements Serializable {
    //主键
    private Long id;

    //分类名称
    private String name;


}