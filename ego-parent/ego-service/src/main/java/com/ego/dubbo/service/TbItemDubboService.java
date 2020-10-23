package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
/**
 * duubo接口中一般只操作数据库的增删改查，不涉及业务逻辑
 * @author hl43674824
 *
 */
public interface TbItemDubboService {
	/**
	 * 商品分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid show(int page,int rows);
	/**
	 * 根据商品编号修改商品状态
	 * @param item
	 * @return
	 */
	int updateItemStatus(TbItem item);
	/**
	 * 新增商品
	 * @param item
	 * @return
	 */
	int insTbItem(TbItem item);
	/**
	 * 新增商品，包括商品信息，描述以及规格参数，考虑事务回滚
	 * @param item
	 * @param desc
	 * @return
	 */
	int insTbItemDesc(TbItem item,TbItemDesc desc,TbItemParamItem paramItem) throws Exception;
	/**
	 * 查询所有状态正常的商品
	 * @param status
	 * @return
	 */
	List<TbItem> selAll(byte status);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TbItem selById(long id);
}
