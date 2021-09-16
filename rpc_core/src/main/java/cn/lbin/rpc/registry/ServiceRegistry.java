package cn.lbin.rpc.registry;

import cn.lbin.rpc.enumeration.RpcError;
import cn.lbin.rpc.exception.RpcException;
import cn.lbin.rpc.registry.redis.RedisServiceRegistry;

import java.net.InetSocketAddress;

public interface ServiceRegistry {

    void registry(String interfaceName, InetSocketAddress address);

    static ServiceRegistry getByCode(int code){
        switch (code){
            case 0:
                return new RedisServiceRegistry();
            default:
                throw new RpcException(RpcError.REGISTRY_NOT_FIND);
        }
    }
}
