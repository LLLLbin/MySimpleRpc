package cn.lbin.rpc.transport;

import cn.lbin.rpc.entity.RPCRequest;
import cn.lbin.rpc.entity.RPCResponse;
import cn.lbin.rpc.enumeration.PackageType;
import cn.lbin.rpc.enumeration.RpcError;
import cn.lbin.rpc.exception.RpcException;
import cn.lbin.rpc.serializer.CommonSerialize;
import cn.lbin.rpc.serializer.JsonSerializer;

import java.io.IOException;
import java.io.OutputStream;

public class ObjectWriter {
    private static final int MAGICNUMBER = 0xCAFEBABE;

    public static void write(OutputStream outputStream,
                             CommonSerialize serialize, Object object) throws IOException {
        byte[] array;
//        将魔数写出去
        array = intToByteArray(MAGICNUMBER);
        outputStream.write(array);
//        写出本次发送的类型，是请求还是响应
        if (object instanceof RPCResponse) {
            array = intToByteArray(PackageType.RESPONSE_TYPE.getCode());
        } else if (object instanceof RPCRequest) {
            array = intToByteArray(PackageType.REQUEST_TYPE.getCode());
        }else{
            throw new RpcException(RpcError.ILLEGAL_OBJECT);
        }
        outputStream.write(array);
//        写出本次序列化的类型
        array = intToByteArray(serialize.getCode());
        outputStream.write(array);
//        使用标明的序列化类型将RPCRequest及其长度写出去
        byte[] bytes = new JsonSerializer().serialize(object);
        outputStream.write(intToByteArray(bytes.length));
        outputStream.write(bytes);
    }

    public static byte[] intToByteArray(int number) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((number >> 24) & 0xFF);
        bytes[1] = (byte) ((number >> 16) & 0xFF);
        bytes[2] = (byte) ((number >> 8) & 0xFF);
        bytes[3] = (byte) (number & 0xFF);
        return bytes;
    }
}
