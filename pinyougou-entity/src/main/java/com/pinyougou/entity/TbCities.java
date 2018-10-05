package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 城市
 *
 */
@Data
public class TbCities implements Serializable {
    //主键
    private Integer id;

    //城市ID
    private String cityid;

    //城市名称
    private String city;

    //省份ID
    private String provinceid;


}