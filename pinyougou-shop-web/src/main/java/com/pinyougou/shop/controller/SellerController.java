package com.pinyougou.shop.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbSeller;
import com.pinyougou.page.PageResult;
import com.pinyougou.result.JsonResult;
import com.pinyougou.sellergoods.service.ISellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {

    @Reference
    private ISellerService sellerService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<TbSeller> findAll() {
        return sellerService.findAll();
    }


    /**
     * 分页查询
     *
     * @return
     */
    @PostMapping("/search")
    public PageResult findPage(@RequestBody TbSeller seller,
                               @RequestParam(defaultValue = "1", required = false) int page,
                               @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("分页请求商家数据...");
        return sellerService.search(seller, page, size);
    }

    /**
     * 增加
     *
     * @param seller
     * @return
     */
    @PostMapping("/")
    public JsonResult add(@RequestBody TbSeller seller) {
        //密码加密
        //BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        //String password = passwordEncoder.encode(seller.getPassword());//加密
        //seller.setPassword(password);

        try {
            sellerService.add(seller);
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
     * @param seller
     * @return
     */
    @PutMapping("/{id}")
    public JsonResult update(@PathVariable String id, @RequestBody TbSeller seller) {
        try {
            seller.setSellerId(id);
            sellerService.update(seller);
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
    public TbSeller findOne(@PathVariable String id) {
        log.info("查找Id为{}的品牌数据！", id);
        return sellerService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/")
    public JsonResult delete(@RequestBody String[] ids) {
        try {
            sellerService.delete(ids);
            log.info("删除成功...");
            return JsonResult.ok("删除成功");
        } catch (Exception e) {
            log.error("删除失败：{}", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
    }
}
