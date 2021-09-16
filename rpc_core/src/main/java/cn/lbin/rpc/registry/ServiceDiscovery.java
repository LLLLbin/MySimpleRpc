package cn.lbin.rpc.registry;

import cn.lbin.rpc.loadbalance.CommonLoadBalance;

import java.net.InetSocketAddress;

public interface ServiceDiscovery {
    public InetSocketAddress getInetByInterfaceName(String interfaceName);
}
