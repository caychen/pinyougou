package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 省
 */
@Data
public class TbProvinces implements Serializable {
    private Integer id;

    //省份ID
    private String provinceid;

    //省份名称
    private String province;


}