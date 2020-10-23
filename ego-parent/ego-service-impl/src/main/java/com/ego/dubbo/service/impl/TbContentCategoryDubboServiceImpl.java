package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;

public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {
	@Resource
	private TbContentCategoryMapper tbContentCategoryMapper;
	@Override
	public List<TbContentCategory> selByPid(long pid) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(pid).andStatusEqualTo(1);
		return tbContentCategoryMapper.selectByExample(example);
	}
	@Override
	public int insCategoryBy(TbContentCategory cate,TbContentCategory parent) throws Exception {
		int index = tbContentCategoryMapper.insertSelective(cate);
		index += tbContentCategoryMapper.updateByPrimaryKeySelective(parent);
		if(index == 2) {
			return 1;
		}else {
			throw new Exception("新增失败，失误回滚");
		}
	}
	@Override
	public int update(TbContentCategory cate) {
		return tbContentCategoryMapper.updateByPrimaryKey(cate);
	}
	@Override
	public TbContentCategory selById(long id) {
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}
}
