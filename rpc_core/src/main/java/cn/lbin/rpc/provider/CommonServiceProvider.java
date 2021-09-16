package cn.lbin.rpc.provider;

import java.net.InetSocketAddress;

public interface CommonServiceProvider {
    <T>void addProvider(T object, String interfaceName, InetSocketAddress address);

    Object getProvider(String interfaceName);
}
