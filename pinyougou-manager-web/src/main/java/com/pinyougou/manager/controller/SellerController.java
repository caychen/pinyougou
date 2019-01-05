package com.pinyougou.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbSeller;
import com.pinyougou.enums.MsgEnum;
import com.pinyougou.enums.StatusEnum;
import com.pinyougou.page.PageResult;
import com.pinyougou.result.JsonResult;
import com.pinyougou.sellergoods.service.ISellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {

    /**
     * 返回全部列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<TbSeller> findAll() {
        return sellerService.findAll();
    }

    @Reference
    private ISellerService sellerService;


    /**
     * 分页查询
     *
     * @return
     */
    @PostMapping("/search")
    public PageResult findPage(@RequestBody TbSeller seller,
                               @RequestParam(defaultValue = "1", required = false) int page,
                               @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("分页请求商家数据：seller=[{}], page=[{}], size=[{}]", seller, page, size);
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
        log.info("添加商家数据：seller=[{}]", seller);
        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(seller.getPassword());//加密
        seller.setPassword(password);

        try {
            sellerService.add(seller);
            return JsonResult.ok();
        } catch (Exception e) {
            log.error("添加失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail(MsgEnum.ADD_FAILED.getMsg());
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
            log.info("修改商家数据：seller=[{}]", seller);
            sellerService.update(seller);
            return JsonResult.ok();
        } catch (Exception e) {
            log.error("修改失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail(MsgEnum.UPDATE_FAILED.getMsg());
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
        log.info("查找Id为[{}]的商家数据！", id);
        TbSeller one = sellerService.findOne(id);
        log.info("查询到的商家数据为：[{}]", one);
        return one;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/")
    public JsonResult delete(@RequestBody String[] ids) {
        log.info("删除商家数据：ids=[{}]", Arrays.asList(ids));
        try {
            sellerService.delete(ids);
            return JsonResult.ok();
        } catch (Exception e) {
            log.error("删除失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail(MsgEnum.DELETE_FAILED.getMsg());
        }
    }

    @PutMapping("/status/{sellerId}")
    public JsonResult updateStatus(@PathVariable String sellerId, String status) {
        log.info("商家审核id:[{}]，修改状态为:[{}]", sellerId, StatusEnum.getDescByCode(status).getDesc());
        try {
            sellerService.updateStatus(sellerId, status);
            return JsonResult.ok();
        } catch (Exception e) {
            log.error("修改状态失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail();
        }
    }
}
