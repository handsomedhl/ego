package solrj;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * solr集群，使用zookeeper进行solr之间的通信
 * 新增，修改，删除都需要提交
 * @author hl43674824
 *
 */
public class TestCluster {
	private CloudSolrClient client = new CloudSolrClient("192.168.182.134:2181,192.168.182.134:2182,192.168.182.134:2183");
	@Before
	public void before() {
		client.setDefaultCollection("collection1");
	}
	@After
	public void after() throws SolrServerException, IOException {
		client.commit();
		client.close();
	}
//	@Test
	public void testInsert() throws SolrServerException, IOException {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", 4);
		doc.addField("ik", "深入理解java虚拟机");
		doc.addField("ik1", "大数据4");
		client.add(doc);
	}
//	@Test
	public void testDelete() throws SolrServerException, IOException {
		client.deleteById("2");
	}
	@Test
	public void testQuery() throws SolrServerException, IOException {
		//可视化界面左侧条件
		SolrQuery param = new SolrQuery();
		//设置q，查询条件
		param.setQuery("item_title:手机");
		//设置从第几条开始查询
		param.setStart(0);
		//设置查询多少条数据
		param.setRows(4);
		
		//启动高亮
		param.setHighlight(true);
		//设置高亮列
		param.addHighlightField("item_title");
		//设置前缀
		param.setHighlightSimplePre("<span style='color:red;'>");
		//设置后缀
		param.setHighlightSimplePost("</span>");
		
		
		//相当于点击查询按钮，本质上是发送http请求到solr，然后接受响应，response对象中包含了返回的json数据
		QueryResponse response = client.query(param);
		//获取高亮元素
		Map<String, Map<String, List<String>>> hh = response.getHighlighting();
		System.out.println(hh);
		//取出doc{}，放入ArrayList中
		SolrDocumentList results = response.getResults();
		for (SolrDocument doc : results) {
			System.out.println(doc.getFieldValue("id"));
			System.out.println("未高亮"+doc.getFieldValue("item_title"));
			Map<String, List<String>> map = hh.get(doc.getFieldValue("id"));
			System.out.println(map);
			List<String> list = map.get("item_title");
			if(list != null && list.size() > 0) {
				System.out.println("高亮"+list.get(0));
			}else {
				System.out.println("没有高亮内容");
			}
		}
	}
}
