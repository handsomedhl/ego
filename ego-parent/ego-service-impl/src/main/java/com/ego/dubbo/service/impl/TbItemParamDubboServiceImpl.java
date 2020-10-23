package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {
	@Resource
	private TbItemParamMapper tbItemParamMapper;
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		//设置分页条件
		PageHelper.startPage(page, rows);
		//执行查询
		//当数据库字段中带有一个或多个text类型的字段时，逆向工程会自动生成xxxWithBloBs方法
		//因为text表示为大文本数据，访问效率较慢，为提升效率，因此只有带有xxxWithBloBs的方法才会对text字段操作
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		//将查询结果封装到分页信息
		PageInfo<TbItemParam> pi = new PageInfo<>(list);
		//获取到相应的返回信息
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		return dataGrid;
	}
	@Override
	public int delByIds(String ids) throws Exception {
		String[] split = ids.split(",");
		int index = 0;
		for (String string : split) {
			index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(string));
		}
		if(index == split.length) {
			return 1;
		}else {
			throw new Exception("删除失败。可能原因：删除信息已不存在");
		}
	}
	@Override
	public TbItemParam selById(long id) {
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(id);
		List<TbItemParam> itemParam = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if(itemParam != null && itemParam.size() > 0) {
			//前提：一个商品类目只能对应一个规格参数模板
			return itemParam.get(0);
		}
		return null;
	}
	@Override
	public int insItemParam(TbItemParam param) {
		return tbItemParamMapper.insertSelective(param);
	}

}
