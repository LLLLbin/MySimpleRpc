package cn.lbin.rpc.loadbalance;

import cn.lbin.rpc.enumeration.RpcError;
import cn.lbin.rpc.exception.RpcException;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class RandomBanlance implements CommonLoadBalance {
    @Override
    public InetSocketAddress select(Set<String> set) {
        if (set == null || set.isEmpty()) {
            throw new RpcException(RpcError.INSTANCE_NOT_FIND);
        }
        int size = set.size();
        int anInt = new Random().nextInt(size);
        Iterator<String> iterator = set.iterator();
        int count = 0;
        if (count == anInt) {
            return strinToAddress(iterator.next());
        }
        while (count != anInt) {
            iterator.next();
            count++;
        }
        return strinToAddress(iterator.next());
    }

    public InetSocketAddress strinToAddress(String string) {
        String[] split = string.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }
}
