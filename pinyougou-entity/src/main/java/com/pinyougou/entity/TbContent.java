package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 内容
 *
 */
@Data
public class TbContent implements Serializable {
    private Long id;

    //内容类目ID
    private Long categoryId;

    //内容标题
    private String title;

    //链接
    private String url;

    //图片绝对路径
    private String pic;

    //状态
    private String status;

    //排序
    private Integer sortOrder;


}