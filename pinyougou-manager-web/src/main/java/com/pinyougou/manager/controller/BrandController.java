package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbBrand;
import com.pinyougou.page.PageResult;
import com.pinyougou.result.JsonResult;
import com.pinyougou.sellergoods.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
        log.info("分页请求品牌...");
        return brandService.findPage(page, size);
    }

    @PostMapping("/add")
    public JsonResult add(@RequestBody TbBrand brand) {
        log.info("添加品牌...");
        try {
            brandService.add(brand);
            log.info("添加成功...");
            return JsonResult.ok("添加成功");
        } catch (Exception e) {
            log.error("添加失败：{}", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("添加失败");
        }
    }
}
