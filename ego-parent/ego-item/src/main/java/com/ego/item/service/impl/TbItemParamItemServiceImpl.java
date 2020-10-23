package com.ego.item.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemParamItemDubboService;
import com.ego.item.pojo.ParamItem;
import com.ego.item.pojo.ParamNode;
import com.ego.item.service.TbItemParamItemService;
import com.ego.pojo.TbItemParamItem;
@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {
	@Reference
	private TbItemParamItemDubboService tbItemParamItemDubboService;
	@Override
	public String showItemParam(long itemid) {
		TbItemParamItem param = tbItemParamItemDubboService.selByItemId(itemid);
		List<ParamItem> paramList = JsonUtils.jsonToList(param.getParamData(), ParamItem.class);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < paramList.size(); i++) {
			sb.append("<table width='500' style='color:gray'>");
			ParamItem item = paramList.get(i);
			List<ParamNode> params = item.getParams();
			for (int j = 0; j < params.size(); j++) {
				sb.append("<tr>");
				if(j == 0) {
					sb.append("<td align='right' width='30%'>"+item.getGroup()+"</td>");
					
				}else {
					sb.append("<td></td>");
				}
				sb.append("<td align='right' width='30%'>"+params.get(j).getK()+"</td>");
				sb.append("<td>"+params.get(j).getV()+"</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("<hr style='color:gray'>");
		}
		return sb.toString();
	}

}
