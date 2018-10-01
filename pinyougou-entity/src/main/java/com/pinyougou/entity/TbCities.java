package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbCities implements Serializable {
    private Integer id;

    private String cityid;

    private String city;

    private String provinceid;


}