package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbGoods;
import com.pinyougou.group.GoodsGroup;
import com.pinyougou.page.PageResult;
import com.pinyougou.result.JsonResult;
import com.pinyougou.sellergoods.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Reference
    private IGoodsService goodsService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<TbGoods> findAll() {
        log.info("请求/goods/list...");
        return goodsService.findAll();
    }


    /**
     * 分页查询
     *
     * @return
     */
    @PostMapping("/search")
    public PageResult findPage(@RequestBody TbGoods goods,
                               @RequestParam(defaultValue = "1", required = false) int page,
                               @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("分页请求商品数据...");
        return goodsService.search(goods, page, size);
    }

    /**
     * 增加
     *
     * @param goodsGroup
     * @return
     */
    @PostMapping("/")
    public JsonResult add(@RequestBody GoodsGroup goodsGroup) {
        log.info("添加商品数据...");
        try {
            //获取商家id
            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();

            //设置商家id
            goodsGroup.getGoods().setSellerId(sellerId);

            goodsService.add(goodsGroup);
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
     * @param goods
     * @return
     */
    @PutMapping("/{id}")
    public JsonResult update(@PathVariable Long id, @RequestBody TbGoods goods) {
        log.info("修改品牌数据...");
        try {
            goods.setId(id);
            goodsService.update(goods);
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
    public TbGoods findOne(@PathVariable Long id) {
        log.info("查找Id为{}的品牌数据！", id);
        return goodsService.findOne(id);
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
            goodsService.delete(ids);
            log.info("删除成功...");
            return JsonResult.ok("删除成功");
        } catch (Exception e) {
            log.error("删除失败：{}", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
    }

}
