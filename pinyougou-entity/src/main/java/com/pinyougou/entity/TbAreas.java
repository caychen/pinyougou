package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 县区
 */
@Data
public class TbAreas implements Serializable {
    //主键
    private Integer id;

    //区域ID
    private String areaid;

    //区域名称
    private String area;

    //城市ID
    private String cityid;

}