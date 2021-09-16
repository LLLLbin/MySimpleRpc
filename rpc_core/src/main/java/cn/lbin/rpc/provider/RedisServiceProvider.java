package cn.lbin.rpc.provider;

import cn.lbin.rpc.util.RedisUtil;

import java.net.InetSocketAddress;

public class RedisServiceProvider extends AbstractServiceProvider {

    @Override
    public <T> void addProvider(T object, String interfaceName, InetSocketAddress address) {
        if (registry.containsKey(address)) {
            return;
        }
        RedisUtil.registry(interfaceName, address);
        registry.put(interfaceName, object);
    }
}
