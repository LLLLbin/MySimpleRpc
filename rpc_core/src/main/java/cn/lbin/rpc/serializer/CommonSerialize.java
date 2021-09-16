package cn.lbin.rpc.serializer;

import cn.lbin.rpc.enumeration.RpcError;
import cn.lbin.rpc.exception.RpcException;

public interface CommonSerialize {
    byte[] serialize(Object object);

    <T> T deSerialize(byte[] bytes, Class<T> clazz);

    int getCode();

    static CommonSerialize getByCode(int code) {
        switch (code) {
            case 0:
                return new JsonSerializer();
            default:
                throw new RpcException(RpcError.SERIALIZE_NOT_FIND);
        }
    }
}
