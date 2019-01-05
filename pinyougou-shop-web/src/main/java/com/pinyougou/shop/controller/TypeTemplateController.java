package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.TbTypeTemplate;
import com.pinyougou.enums.MsgEnum;
import com.pinyougou.page.PageResult;
import com.pinyougou.result.JsonResult;
import com.pinyougou.sellergoods.service.ITypeTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
@Slf4j
public class TypeTemplateController {

	@Reference
	private ITypeTemplateService typeTemplateService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@GetMapping("/list")
	public List<TbTypeTemplate> findAll(){
		log.info("请求/typeTemplate/list...");
		return typeTemplateService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@PostMapping("/search")
	public PageResult search(@RequestBody TbTypeTemplate typeTemplate,
							 @RequestParam(defaultValue = "1", required = false) int page,
							 @RequestParam(defaultValue = "10", required = false) int size){
		log.info("分页请求模板：typeTemplate=[{}], page=[{}], size=[{}]", typeTemplate, page, size);
		return typeTemplateService.search(typeTemplate, page, size);
	}

	/**
	 * 增加
	 * @param typeTemplate
	 * @return
	 */
	@PostMapping("/")
	public JsonResult add(@RequestBody TbTypeTemplate typeTemplate){
		log.info("添加模板数据：typeTemplate=[{}]", typeTemplate);
		try {
			typeTemplateService.add(typeTemplate);
			return JsonResult.ok();
		} catch (Exception e) {
			log.error("添加失败原因：[{}]", e.getMessage());
			e.printStackTrace();
			return JsonResult.fail(MsgEnum.ADD_FAILED.getMsg());
		}
	}
	
	/**
	 * 修改
	 * @param typeTemplate
	 * @return
	 */
	@PutMapping("/{id}")
	public JsonResult update(@PathVariable Long id, @RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplate.setId(id);
			log.info("修改模板数据, typeTemplate=[{}]", typeTemplate);
			typeTemplateService.update(typeTemplate);
			return JsonResult.ok();
		} catch (Exception e) {
			log.error("修改失败原因：[{}]", e.getMessage());
			e.printStackTrace();
			return JsonResult.fail(MsgEnum.UPDATE_FAILED.getMsg());
		}
	}

	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public TbTypeTemplate findOne(@PathVariable Long id){
		log.info("查找Id为[{}]的模板数据！", id);
		TbTypeTemplate one = typeTemplateService.findOne(id);
		log.info("查询到的模板数据为：[{}]", one);
		return one;
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/")
	public JsonResult delete(@RequestBody Long [] ids){
		log.info("删除模板数据：ids=[{}]", Arrays.asList(ids));
		try {
			typeTemplateService.delete(ids);
			return JsonResult.ok();
		} catch (Exception e) {
			log.error("删除失败原因：[{}]", e.getMessage());
			e.printStackTrace();
			return JsonResult.fail(MsgEnum.DELETE_FAILED.getMsg());
		}
	}

	@GetMapping("/options")
	public List<Map> selectTypeTemplateOptionList(){
		return typeTemplateService.selectTypeTemplateOptionList();
	}
	
}
