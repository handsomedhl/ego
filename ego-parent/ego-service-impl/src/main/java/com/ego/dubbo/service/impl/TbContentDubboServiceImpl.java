package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbContentDubboServiceImpl implements TbContentDubboService {
	@Resource
	private TbContentMapper tbContentMapper;
	@Override
	public EasyUIDataGrid selByCategoryId(long categoryId, int page, int rows) {
		//设置分页条件
		PageHelper.startPage(page,rows);
		TbContentExample example = new TbContentExample();
		if(categoryId != 0) {
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbContent> pi = new PageInfo<TbContent>(list);
		EasyUIDataGrid data = new EasyUIDataGrid();
		data.setRows(pi.getList());
		data.setTotal(pi.getTotal());
		return data;
	}
	@Override
	public int insTbContent(TbContent content) {
		return tbContentMapper.insertSelective(content);
	}
	@Override
	public List<TbContent> selByCount(int count, boolean isSort) {
		TbContentExample example = new TbContentExample();
		if(isSort) {
			example.setOrderByClause("updated desc");
		}
		if(count != 0) {
			PageHelper.startPage(1, count);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
			return pageInfo.getList();
		}else {
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
	}
}
