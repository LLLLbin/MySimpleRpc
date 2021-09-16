package cn.lbin.rpc.transport;

import cn.lbin.rpc.entity.RPCRequest;
import cn.lbin.rpc.entity.RPCResponse;
import cn.lbin.rpc.enumeration.PackageType;
import cn.lbin.rpc.enumeration.RpcError;
import cn.lbin.rpc.exception.RpcException;
import cn.lbin.rpc.serializer.CommonSerialize;
import cn.lbin.rpc.serializer.JsonSerializer;

import java.io.IOException;
import java.io.InputStream;

public class ObjectReader {
    private static final int MAGICNUMBER = 0xCAFEBABE;

    public static Object read(InputStream inputStream) throws IOException {
        byte[] array = new byte[4];
//        接收魔数
        inputStream.read(array);
        int magicNumber = byteArrayToInt(array);
        if (magicNumber != MAGICNUMBER) {
            throw new RpcException(RpcError.MAGICNUMBER_ERROR);
        }
//        接收传输类型
        inputStream.read(array);
        Class<?> packageType = null;
        int packageNumber = byteArrayToInt(array);
        if (packageNumber == PackageType.REQUEST_TYPE.getCode()) {
            packageType = RPCRequest.class;
        } else if (packageNumber == PackageType.RESPONSE_TYPE.getCode()) {
            packageType = RPCResponse.class;
        }else{
            throw new RpcException(RpcError.PACKAGE_TYPE_NOT_FIND);
        }

//        接收序列化类型并获取得到对应的序列化器
        inputStream.read(array);
        int serializeNumber = byteArrayToInt(array);
        CommonSerialize commonSerialize=CommonSerialize.getByCode(serializeNumber);

//        接收响应体RPCResponse的长度并通过反序列化得到真实对象
        inputStream.read(array);
        int responseLength = byteArrayToInt(array);
        byte[] body = new byte[responseLength];
        inputStream.read(body, 0, responseLength);
//        根据序列化类型，将响应体反序列化
        Object object = commonSerialize.deSerialize(body, packageType);
        if (object==null){
            throw new RpcException(RpcError.UNKNOW_ERROR);
        }
        return object;
    }

    //    TODO
    public static int byteArrayToInt(byte[] src) {
        int value;
        value = ((src[0] & 0xFF) << 24)
                | ((src[1] & 0xFF) << 16)
                | ((src[2] & 0xFF) << 8)
                | (src[3] & 0xFF);
        return value;
    }
}
