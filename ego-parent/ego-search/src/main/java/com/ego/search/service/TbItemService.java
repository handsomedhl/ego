package com.ego.search.service;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

public interface TbItemService {
	/**
	 * Solr数据初始化
	 */
	void init() throws SolrServerException, IOException;
	/**
	 * 根据关键字搜索商品
	 * @param q
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String,Object> search(String q,int page,int rows) throws SolrServerException, IOException ;
	/**
	 * 新增商品信息到solr中
	 * @param map
	 * @param desc
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	int add(Map<String,Object> map,String desc) throws SolrServerException, IOException;
}
