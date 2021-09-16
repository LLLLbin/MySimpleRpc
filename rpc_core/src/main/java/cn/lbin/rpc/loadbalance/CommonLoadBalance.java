package cn.lbin.rpc.loadbalance;

import cn.lbin.rpc.entity.RPCInstance;
import cn.lbin.rpc.enumeration.RpcError;
import cn.lbin.rpc.exception.RpcException;
import cn.lbin.rpc.serializer.CommonSerialize;
import cn.lbin.rpc.serializer.JsonSerializer;

import java.net.InetSocketAddress;
import java.util.Set;

public interface CommonLoadBalance {

    public InetSocketAddress select(Set<String> set);

    static CommonLoadBalance getByCode(int code) {
        switch (code) {
            case 0:
                return new RandomBanlance();
            default:
                throw new RpcException(RpcError.SERIALIZE_NOT_FIND);
        }
    }
}
