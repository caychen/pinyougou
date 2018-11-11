package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbItemCat;
import com.pinyougou.page.PageResult;
import com.pinyougou.result.JsonResult;
import com.pinyougou.sellergoods.service.IItemCatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/itemCat")
@Slf4j
public class ItemCatController {

    @Reference
    private IItemCatService itemCatService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<TbItemCat> findAll() {
        log.info("请求/itemCat/list...");
        return itemCatService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @PostMapping("/search")
    public PageResult findPage(@RequestBody TbItemCat itemCat,
                               @RequestParam(defaultValue = "1", required = false) int page,
                               @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("分页请求分类数据...");
        return itemCatService.search(itemCat, page, size);
    }

    /**
     * 增加
     *
     * @param itemCat
     * @return
     */
    @PostMapping("/")
    public JsonResult add(@RequestBody TbItemCat itemCat) {
        log.info("添加分类数据...");
        try {
            itemCatService.add(itemCat);
            log.info("添加成功...");
            return JsonResult.ok("添加成功");
        } catch (Exception e) {
            log.error("添加失败：{}", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("添加失败");
        }
    }

    /**
     * 修改
     *
     * @param itemCat
     * @return
     */
    @PutMapping("/{id}")
    public JsonResult update(@PathVariable Long id, @RequestBody TbItemCat itemCat) {
        log.info("修改分类数据...");
        try {
            itemCat.setId(id);
            itemCatService.update(itemCat);
            log.info("修改成功...");
            return JsonResult.ok("修改成功");
        } catch (Exception e) {
            log.error("修改失败：{}", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public TbItemCat findOne(@PathVariable Long id) {
        log.info("查找Id为{}的品牌数据！", id);
        return itemCatService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/")
    public JsonResult delete(@RequestBody Long[] ids) {
        log.info("删除品牌数据：{}", Arrays.asList(ids));
        try {
            itemCatService.delete(ids);
            log.info("删除成功...");
            return JsonResult.ok("删除成功");
        } catch (Exception e) {
            log.error("删除失败：{}", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
    }

    /**
     * 根据上级ID查询商品分类列表
     *
     * @param parentId
     * @return
     */
    @GetMapping("/sub")
    public List<TbItemCat> findByParentId(@RequestParam("pId") Long parentId) {
        return itemCatService.findByParentId(parentId);
    }

}
