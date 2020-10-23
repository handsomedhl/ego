package com.ego.manage.pojo;

import java.io.Serializable;

import com.ego.pojo.TbItemParam;

public class TbItemParamChild extends TbItemParam implements Serializable{
	private String itemCatName;

	public String getItemCatName() {
		return itemCatName;
	}

	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}
}
