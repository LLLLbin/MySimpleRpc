package cn.lbin.rpc.handler;

import cn.lbin.rpc.entity.RPCRequest;
import cn.lbin.rpc.serializer.CommonSerialize;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public interface CommonHandler {
    void response(Socket socket, CommonSerialize serialize) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    Object invoke(RPCRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
