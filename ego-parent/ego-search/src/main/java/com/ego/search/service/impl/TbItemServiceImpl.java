package com.ego.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemDesc;
import com.ego.search.pojo.TbItemChild;
import com.ego.search.service.TbItemService;
/**
 * addField:当该属性有值时，就不设置值
 * setField：无论该属性是否有值，都设置值
 * @author hl43674824
 *
 */
@Service
public class TbItemServiceImpl implements TbItemService {
	@Reference
	private TbItemDubboService tbItemDubboService;
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	@Reference
	private TbItemDescDubboService tbItemDescDubboService;
	@Resource
	private CloudSolrClient solrClient;
	@Override
	public void init() throws SolrServerException, IOException {
		List<TbItem> itemList = tbItemDubboService.selAll((byte)1);
		for (TbItem tbItem : itemList) {
			TbItemCat itemCat = tbItemCatDubboService.selById(tbItem.getCid());
			TbItemDesc itemDesc = tbItemDescDubboService.selById(tbItem.getId());
			
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", tbItem.getId());
			doc.addField("item_title", tbItem.getTitle());
			doc.addField("item_sell_point", tbItem.getSellPoint());
			doc.addField("item_price", tbItem.getPrice());
			doc.addField("item_image", tbItem.getImage());
			doc.addField("item_category_name", itemCat.getName());
			doc.addField("item_desc", itemDesc.getItemDesc());
			doc.addField("item_updated", tbItem.getUpdated());
			solrClient.add(doc);
		}
		solrClient.commit();
	}
	@Override
	public Map<String, Object> search(String q, int page, int rows) throws SolrServerException, IOException {
		SolrQuery params = new SolrQuery();
		params.setQuery("item_keywords:"+q);
		//设置查询条件
		//设置分页条件
		params.setStart(rows * (page - 1));
		params.setRows(rows);
		//设置按照修改时间降序排序
		params.addSort("item_updated", ORDER.desc);
		
		//启动高亮
		params.setHighlight(true);
		params.addHighlightField("item_title");
		params.setHighlightSimplePre("<span style='color:red'>");
		params.setHighlightSimplePost("</span>");
		QueryResponse response = solrClient.query(params);
		Map<String, Map<String, List<String>>> hh = response.getHighlighting();
		SolrDocumentList solrDocument = response.getResults();
		//存放查询出商品信息的内容
		List<TbItemChild> itemList = new ArrayList<>();
		for (SolrDocument doc : solrDocument) {
			TbItemChild item = new TbItemChild();
			item.setId(Long.parseLong(doc.getFieldValue("id").toString()));
			List<String> list = (hh.get(doc.getFieldValue("id")).get("item_title"));
			//判断是否有高亮内容
			if(list != null && list.size() > 0) {
				item.setTitle(list.get(0));
			}else {
				item.setTitle((doc.getFieldValue("item_title").toString()));
			}
			//图片是数组的格式
			Object images = doc.getFieldValue("item_image");
			item.setImages(images==null||images.equals("")?new String[1]:images.toString().split(","));
			item.setPrice(Long.parseLong(doc.getFieldValue("item_price").toString()));
			item.setSellPoint(doc.getFieldValue("item_sell_point").toString());
			itemList.add(item);
		}
		//封装返回的格式
		Map<String,Object> map = new HashMap<>();
		map.put("itemList", itemList);
		map.put("totalPages", solrDocument.getNumFound()%rows==0?solrDocument.getNumFound():solrDocument.getNumFound()+1);
		return map;
	}
	@Override
	public int add(Map<String, Object> map, String desc) throws SolrServerException, IOException {
		SolrInputDocument doc = new SolrInputDocument();
		System.out.println(map);
		System.out.println(map.get("title"));
		doc.setField("id", map.get("id"));
		doc.setField("item_title", map.get("title"));
		doc.setField("item_sell_point", map.get("sellPoint"));
		doc.setField("item_price", map.get("price"));
		doc.setField("item_image", map.get("image"));
		doc.setField("item_category_name", tbItemCatDubboService.selById((Integer)map.get("cid")));
		doc.setField("item_desc", desc);
		doc.setField("item_updated", new Date());
		UpdateResponse response = solrClient.add(doc);
		solrClient.commit();
		System.out.println(response);
		if(response.getStatus() == 0) {
			return 1;
		}
		return 0;
	}

}
