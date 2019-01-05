package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbBrand;
import com.pinyougou.enums.MsgEnum;
import com.pinyougou.page.PageResult;
import com.pinyougou.result.JsonResult;
import com.pinyougou.sellergoods.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
@Slf4j
public class BrandController {

    @Reference
    private IBrandService brandService;

    @GetMapping("/list")
    public List<TbBrand> findAll() {
        log.info("请求/brand/list...");
        return brandService.findAll();
    }

    @PostMapping("/search")
    public PageResult findPage(@RequestBody TbBrand brand,
                               @RequestParam(defaultValue = "1", required = false) int page,
                               @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("分页请求品牌数据：brand=[{}], page=[{}], size=[{}]", brand, page, size);
        return brandService.search(brand, page, size);
    }

    @PostMapping("/")
    public JsonResult add(@RequestBody TbBrand brand) {
        log.info("添加品牌数据：brand=[{}]", brand);
        try {
            brandService.add(brand);
            return JsonResult.ok();
        } catch (Exception e) {
            log.error("添加失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail(MsgEnum.ADD_FAILED.getMsg());
        }
    }

    @GetMapping("/{id}")
    public TbBrand findOne(@PathVariable Long id) {
        log.info("查找Id为[{}]的品牌数据！", id);
        TbBrand one = brandService.findOne(id);
        log.info("查询到的品牌数据为：[{}]", one);
        return one;
    }

    @PutMapping("/{id}")
    public JsonResult update(@PathVariable Long id, @RequestBody TbBrand brand) {
        try {
            brand.setId(id);
            log.info("修改品牌数据：brand=[{}]", brand);
            brandService.update(brand);
            return JsonResult.ok();
        } catch (Exception e) {
            log.error("修改失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail(MsgEnum.UPDATE_FAILED.getMsg());
        }
    }

    @DeleteMapping("/")
    public JsonResult delete(@RequestBody Long[] ids) {
        log.info("删除品牌数据：ids=[{}]", Arrays.asList(ids));
        try {
            brandService.delete(ids);
            return JsonResult.ok();
        } catch (Exception e) {
            log.error("删除失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail(MsgEnum.DELETE_FAILED.getMsg());
        }
    }

    @GetMapping("/options")
    public List<Map> selectBrandOptionList() {
        return brandService.selectBrandOptionList();
    }

}
