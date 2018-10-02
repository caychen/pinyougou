package com.pinyougou.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.page.PageResult
 * Date:         2018/10/2
 * Desc:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {

    private Long total;//总记录数

    private List<?> rows;//当前页记录

}
