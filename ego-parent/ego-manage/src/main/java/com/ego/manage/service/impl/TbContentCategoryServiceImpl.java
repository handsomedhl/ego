package com.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUITree;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.manage.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService{
	@Reference
	private TbContentCategoryDubboService tbContentCategoryDubboService;
	@Override
	public List<EasyUITree> selByPid(long pid) {
		List<TbContentCategory> list = tbContentCategoryDubboService.selByPid(pid);
		List<EasyUITree> treeList = new ArrayList<EasyUITree>();
		for (TbContentCategory cate : list) {
			EasyUITree tree = new EasyUITree();
			tree.setId(cate.getId());
			tree.setText(cate.getName());
			tree.setState(cate.getIsParent()?"closed":"open");
			treeList.add(tree);
		}
		return treeList;
	}
	@Override
	public EgoResult insCategory(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		//判断新增的类目名是否在当前目录中已经存在
		List<TbContentCategory> list = tbContentCategoryDubboService.selByPid(cate.getParentId());
		for (TbContentCategory c : list) {
			if(c.getName().equals(cate.getName())) {
				er.setData("当前类目已存在");
				return er;
			}
		}
		
		Date date = new Date();
		cate.setCreated(date);
		cate.setUpdated(date);
		cate.setSortOrder(1);
		cate.setStatus(1);
		cate.setIsParent(false);
		long id = IDUtils.genItemId();
		cate.setId(id);
		//修改的父类目的信息
		TbContentCategory parent = new TbContentCategory();
		parent.setId(cate.getParentId());
		parent.setIsParent(true);
		int index = 0;
		try {
			 index = tbContentCategoryDubboService.insCategoryBy(cate, parent);
			 if(index > 0) {
				 er.setStatus(200);
				 Map<String,Long> map = new HashMap<>();
				 map.put("id", id);
				 er.setData(map);
			 }
		} catch (Exception e) {
			er.setData(e.getMessage());
			e.printStackTrace();
		}
		return er;
	}
	@Override
	public EgoResult update(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		//判断是否与当前类目的节点同名
		TbContentCategory category = tbContentCategoryDubboService.selById(cate.getId());
		List<TbContentCategory> list = tbContentCategoryDubboService.selByPid(category.getParentId());
		for (TbContentCategory c : list) {
			if(c.getName().equals(cate.getName())) {
				er.setData("当前类目已存在");
				return er;
			}
		}
		category.setUpdated(new Date());
		category.setName(cate.getName());
		int index = tbContentCategoryDubboService.update(category);
		if(index > 0) {
			er.setStatus(200);
		}
		return er;
	}
	@Override
	public EgoResult delete(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		cate.setUpdated(new Date());
		cate.setStatus(2);
		int index = tbContentCategoryDubboService.update(cate);
		if(index > 0) {
			List<TbContentCategory> list = tbContentCategoryDubboService.selByPid(cate.getParentId());
			if(list == null || list.size() == 0) {
				TbContentCategory parent = new TbContentCategory();
				parent.setId(cate.getParentId());
				parent.setIsParent(false);
				index += tbContentCategoryDubboService.update(parent);
				if(index == 2) {
					er.setStatus(200);;
				}
			}
		}
		return er;
	}

}
