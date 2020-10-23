package com.ego.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.item.pojo.PortalMenu;
import com.ego.item.pojo.PortalMenuNode;
import com.ego.item.service.TbItemCatService;
import com.ego.pojo.TbItemCat;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;

	@Override
	public PortalMenu showCatMenu() {
		// 查询出所有一级菜单
		List<TbItemCat> list = tbItemCatDubboServiceImpl.show(0);
		List<Object> portalList = selAllMenu(list);
		PortalMenu menu = new PortalMenu();
		menu.setData(portalList);
		return menu;
	}

	private List<Object> selAllMenu(List<TbItemCat> list) {
		List<Object> portalList = new ArrayList<>();
		for (TbItemCat itemCat : list) {
			if (itemCat.getIsParent()) {
				PortalMenuNode node = new PortalMenuNode();
				node.setU("/products/" + itemCat.getId() + ".html");
				node.setN("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
				node.setI(selAllMenu(tbItemCatDubboServiceImpl.show(itemCat.getId())));
				portalList.add(node);
			} else {
				portalList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
			}
		}
		return portalList;
	}
}
