package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.pinyougou.entity.TbBrand;
import com.pinyougou.page.PageResult;
import com.pinyougou.result.JsonResult;
import com.pinyougou.sellergoods.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @PostMapping("/search")
    public PageResult findPage(@RequestBody TbBrand brand,
                               @RequestParam(defaultValue = "1", required = false) int page,
                               @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("分页请求品牌...");
        return brandService.search(brand, page, size);
    }

    @PostMapping("/")
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

    @GetMapping("/{id}")
    public TbBrand findOne(@PathVariable Long id){
        log.info("查找Id为{}的品牌数据！", id);
        return brandService.findOne(id);
    }

    @PutMapping("/{id}")
    public JsonResult update(@PathVariable Long id, @RequestBody TbBrand brand){
        log.info("修改品牌...");
        try {
            brand.setId(id);
            brandService.update(brand);
            log.info("修改成功...");
            return JsonResult.ok("修改成功");
        } catch (Exception e) {
            log.error("修改失败：{}", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("修改失败");
        }
    }

    @DeleteMapping("/")
    public JsonResult delete(@RequestBody Long[] ids){
        log.info("删除品牌：{}", Arrays.asList(ids));
        try {
            brandService.delete(ids);
            log.info("删除成功...");
            return JsonResult.ok("删除成功");
        } catch (Exception e) {
            log.error("删除失败：{}", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
    }

}
