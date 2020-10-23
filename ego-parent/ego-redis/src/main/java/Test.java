import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ego.redis.dao.impl.JedisDaoImpl;

import redis.clients.jedis.JedisCluster;

public class Test {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext-jedis.xml");
		JedisCluster jedisDaoImpl = app.getBean("jedisClients", JedisCluster.class);
		String string = jedisDaoImpl.get("bigpic");
		System.out.println(string);
	}
}
