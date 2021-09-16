package cn.lbin.rpc.provider;

import cn.lbin.rpc.util.RedisUtil;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ServiceProvider
 * @Description
 * @Author LBin
 * @Date 2021/9/12
 * @Time 10:24
 */
public abstract class AbstractServiceProvider implements CommonServiceProvider{

    static final Map<String, Object> registry = new ConcurrentHashMap<>();

    @Override
    public Object getProvider(String interfaceName) {
        return registry.get(interfaceName);
    }

}
