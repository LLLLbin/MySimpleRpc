package cn.lbin.rpc.serializer;

import cn.lbin.rpc.enumeration.SerializeNumber;
import com.alibaba.fastjson.JSON;

public class JsonSerializer implements CommonSerialize {
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONString(object).getBytes();
    }

    @Override
    public <T> T deSerialize(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(new String(bytes), clazz);
    }

    @Override
    public int getCode() {
        return SerializeNumber.JSON_SERIALIZER.getCode();
    }
}
