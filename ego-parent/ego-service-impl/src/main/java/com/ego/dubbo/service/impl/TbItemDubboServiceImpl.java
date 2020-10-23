package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.ego.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemDubboServiceImpl implements TbItemDubboService{
	@Resource
	private TbItemMapper tbItemMapper;
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	public EasyUIDataGrid show(int page, int rows) {	
		//分页设置
		PageHelper.startPage(page,rows);
		//查询全部
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());
		//分页代码
		//设置分页条件
		PageInfo<TbItem> pi = new PageInfo<TbItem>(list);
		//将分页信息放入实体类中
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		return dataGrid;
	}
	
	@Override
	public int updateItemStatus(TbItem item) {
		return tbItemMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public int insTbItem(TbItem item) {
		return tbItemMapper.insert(item);
	}
	/**
	 * 方法不抛出检查型异常就不必设置 rollbackFor。
	 * 发生运行时异常会自动触发事务回滚，不需要设置rollbackfor
	 */
	@Override
	public int insTbItemDesc(TbItem item, TbItemDesc desc,TbItemParamItem paramItem) throws Exception {
		int index = 0;
		try {
			index += tbItemMapper.insertSelective(item);
			index += tbItemDescMapper.insertSelective(desc);
			index += tbItemParamItemMapper.insertSelective(paramItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(index == 3) {
			return 1;
		}else {
			//自定义抛出异常可以自定义异常信息
			throw new Exception("新增失败，事务回滚");
		}
	}
	@Override
	public List<TbItem> selAll(byte status) {
		TbItemExample example = new TbItemExample();
		example.createCriteria().andStatusEqualTo(status);
		return tbItemMapper.selectByExample(example);
	}

	@Override
	public TbItem selById(long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}

}
