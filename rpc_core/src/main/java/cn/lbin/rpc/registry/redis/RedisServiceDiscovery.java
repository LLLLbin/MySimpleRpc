package cn.lbin.rpc.registry.redis;

import cn.lbin.rpc.loadbalance.CommonLoadBalance;
import cn.lbin.rpc.registry.ServiceDiscovery;
import cn.lbin.rpc.util.RedisUtil;
import lombok.AllArgsConstructor;

import java.net.InetSocketAddress;
import java.util.Set;

@AllArgsConstructor
public class RedisServiceDiscovery implements ServiceDiscovery {

    private CommonLoadBalance loadBalance;

    public RedisServiceDiscovery() {
        this.loadBalance = CommonLoadBalance.getByCode(0);
    }


    @Override
    public InetSocketAddress getInetByInterfaceName(String interfaceName) {
        Set<String> set = RedisUtil.lookup(interfaceName);
        return loadBalance.select(set);
    }
}
