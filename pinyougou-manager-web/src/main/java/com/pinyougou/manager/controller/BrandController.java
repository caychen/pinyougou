package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbBrand;
import com.pinyougou.page.PageResult;
import com.pinyougou.sellergoods.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
@Slf4j
public class BrandController {

    @Reference
    private IBrandService brandService;

    @GetMapping("/list")
    public List<TbBrand> findAll() {
        log.info("请求/brand/list.do...");
        return brandService.findAll();
    }

    @GetMapping("/page")
    public PageResult findPage(@RequestParam(defaultValue = "1", required = false) int page,
                               @RequestParam(defaultValue = "10", required = false) int size) {
        return brandService.findPage(page, size);
    }
}
