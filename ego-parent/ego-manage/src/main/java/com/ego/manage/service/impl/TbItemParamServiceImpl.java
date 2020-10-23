package com.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manage.pojo.TbItemParamChild;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemParam;

@Service
public class TbItemParamServiceImpl implements TbItemParamService{
	@Reference
	private TbItemParamDubboService tbItemParamDubboServiceImpl;
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		EasyUIDataGrid dataGrid = tbItemParamDubboServiceImpl.showPage(page, rows);
		List<TbItemParam> rows2 = (List<TbItemParam>) dataGrid.getRows();
		List<TbItemParamChild> list = new ArrayList<TbItemParamChild>();
		for (TbItemParam tbItemParam : rows2) {
			TbItemParamChild child = new TbItemParamChild();
			child.setId(tbItemParam.getId());
			child.setItemCatId(tbItemParam.getItemCatId());
			child.setItemCatName(tbItemCatDubboServiceImpl.selById(tbItemParam.getItemCatId()).getName());
			child.setParamData(tbItemParam.getParamData());
			child.setCreated(tbItemParam.getCreated());
			child.setUpdated(tbItemParam.getUpdated());
			list.add(child);
		}
		dataGrid.setRows(list);
		return dataGrid;
	}
	@Override
	public EgoResult delByIds(String ids) {
		EgoResult er = new EgoResult();
		int index = 0;
		try {
			index = tbItemParamDubboServiceImpl.delByIds(ids);
			if(index == 1) {
				er.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			er.setData("删除失败，错误提示：<br/>"+e.getMessage());
		}
		return er;
	}
	@Override
	public EgoResult selByCatId(Long id) {
		EgoResult er = new EgoResult();
		TbItemParam itemParam = tbItemParamDubboServiceImpl.selById(id);
		if(itemParam != null) {
			er.setStatus(200);
			er.setData(itemParam);
		}
		return er;
	}
	@Override
	public EgoResult insItemParam(TbItemParam param) {
		EgoResult er = new EgoResult();
		//如果没有参数规格参数信息，直接返回
		if(param.getParamData().equals("[]")) {
			return er;
		}
		Date date = new Date();
		param.setCreated(date);
		param.setUpdated(date);
		int index = tbItemParamDubboServiceImpl.insItemParam(param);
		if(index > 0) {
			er.setStatus(200);
		}
		return er;
	}

}
