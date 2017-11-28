import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hasee on 2017/11/27.
 */
public class TestJedis {

    @Test
    public void testJedis() {
        int port = 7000;
        String host = "redis.voissesw.com";
        Jedis jedis = new Jedis(host, port);
        String key = "test";
        String value = "value";
        int seconds = 10;
        String result = jedis.set(key, value);
        jedis.expire(key, seconds);
        System.out.println(jedis.get(key));
    }

    @Test
    public void testJedisPool() {
        int port = 7000;
        String host = "redis.voissesw.com";
        JedisPool jedisPool = new JedisPool(host, port);
        Jedis jedis = jedisPool.getResource();
        String key = "test";
        String value = "value";
        int seconds = 10;
        String result = jedis.set(key, value);
        jedis.expire(key, seconds);
        System.out.println(jedis.get(key));
        jedis.close();
    }

    @Test
    public void testJedisCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("redis.voissesw.com",7000));
        nodes.add(new HostAndPort("redis.voissesw.com",7001));
        nodes.add(new HostAndPort("redis.voissesw.com",7002));
        nodes.add(new HostAndPort("redis.voissesw.com",7003));
        nodes.add(new HostAndPort("redis.voissesw.com",7004));
        nodes.add(new HostAndPort("redis.voissesw.com",7005));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        String key = "test";
        String value = "value";
        int seconds = 10;
        System.out.println(jedisCluster.set(key, value));
        jedisCluster.expire(key, seconds);
        System.out.println(jedisCluster.get(key));
    }
}
