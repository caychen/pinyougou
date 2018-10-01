package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbBrand;
import com.pinyougou.sellergoods.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
@Slf4j
public class BrandController {

    @Reference
    private IBrandService brandService;

    @RequestMapping("/list")
    public List<TbBrand> findAll(){
        log.info("请求/brand/list.do...");
        return brandService.findAll();
    }
}
