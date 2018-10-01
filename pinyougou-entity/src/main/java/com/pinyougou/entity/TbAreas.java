package com.pinyougou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbAreas implements Serializable {
    private Integer id;

    private String areaid;

    private String area;

    private String cityid;

}