package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbSpecification;
import com.pinyougou.enums.MsgEnum;
import com.pinyougou.group.SpecificationGroup;
import com.pinyougou.page.PageResult;
import com.pinyougou.result.JsonResult;
import com.pinyougou.sellergoods.service.ISpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification")
@Slf4j
public class SpecificationController {

    @Reference
    private ISpecificationService specificationService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<TbSpecification> findAll() {
        log.info("请求/specification/list...");
        return specificationService.findAll();
    }


    /**
     * 分页请求
     *
     * @return
     */
    @PostMapping("/search")
    public PageResult search(@RequestBody TbSpecification specification,
                             @RequestParam(defaultValue = "1", required = false) int page,
                             @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("分页请求规格数据：specification=[{}], page=[{}], size=[{}]", specification, page, size);
        return specificationService.search(specification, page, size);
    }

    /**
     * 增加
     *
     * @param specificationGroup 规格组合实体类
     * @return
     */
    @PostMapping("/")
    public JsonResult add(@RequestBody SpecificationGroup specificationGroup) {
        log.info("添加规格数据：specificationGroup=[{}]", specificationGroup);
        try {
            specificationService.add(specificationGroup);
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
     * @param specificationGroup
     * @return
     */
    @PutMapping("/{id}")
    public JsonResult update(@PathVariable Long id, @RequestBody SpecificationGroup specificationGroup) {
        try {
            specificationGroup.getSpecification().setId(id);
            log.info("修改规格数据：specificationGroup=[{}]", specificationGroup);
            specificationService.update(specificationGroup);
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
    public SpecificationGroup findOne(@PathVariable Long id) {
        log.info("查找Id为[{}]的规格数据！", id);
        return specificationService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/")
    public JsonResult delete(@RequestBody Long[] ids) {
        log.info("删除规格数据：ids=[{}]", Arrays.asList(ids));
        try {
            specificationService.delete(ids);
            return JsonResult.ok();
        } catch (Exception e) {
            log.error("删除失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return JsonResult.fail(MsgEnum.DELETE_FAILED.getMsg());
        }
    }


    @GetMapping("/options")
    public List<Map> selectSpecificationOptionList() {
        return specificationService.selectSpecificationOptionList();
    }

}
