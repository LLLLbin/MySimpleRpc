package cn.lbin.rpc.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.*;

public class RedisUtil {
    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        InputStream inputStream = RedisUtil.class.getResourceAsStream("/redis.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String maxTotal = properties.getProperty("maxTotal");
        String maxIdle = properties.getProperty("maxIdle");
        String minIdle = properties.getProperty("minIdle");
        String blockWhenExhausted = properties.getProperty("blockWhenExhausted");
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String timeout = properties.getProperty("timeout");
        String password = properties.getProperty("password");
        config.setMaxTotal(Integer.parseInt(maxTotal));
        config.setMaxIdle(Integer.parseInt(maxIdle));
        config.setMinIdle(Integer.parseInt(minIdle));
        config.setBlockWhenExhausted(Boolean.parseBoolean(blockWhenExhausted));
        jedisPool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), password);
    }

    public static void registry(String interfaceName, InetSocketAddress address) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(interfaceName, address.getAddress().getHostAddress() + ":" + address.getPort(), String.valueOf(address));
        } finally {
            returnToPool(jedis);
        }
    }

    public static Set<String> lookup(String interfaceName) {
        Jedis jedis = null;
        Map<String, String> map = null;
        try {
            jedis = jedisPool.getResource();
            map = jedis.hgetAll(interfaceName);
            if (map==null){
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnToPool(jedis);
        }
        Set<String> set = map.keySet();
        return set;
    }

    public static void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
