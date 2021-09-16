package cn.lbin.rpc.handler;

import cn.lbin.rpc.entity.RPCRequest;
import cn.lbin.rpc.entity.RPCResponse;
import cn.lbin.rpc.provider.CommonServiceProvider;
import cn.lbin.rpc.provider.RedisServiceProvider;
import cn.lbin.rpc.serializer.CommonSerialize;
import cn.lbin.rpc.transport.ObjectReader;
import cn.lbin.rpc.transport.ObjectWriter;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class JdkHandler implements CommonHandler {

    private CommonServiceProvider provider = new RedisServiceProvider();

    public JdkHandler() {
    }

    public JdkHandler(CommonServiceProvider provider) {
        this.provider = provider;
    }

    @Override
    public void response(Socket socket, CommonSerialize serialize) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        RPCRequest request = (RPCRequest) ObjectReader.read(inputStream);
        Object invoke = invoke(request);
        RPCResponse<Object> response = RPCResponse.success(invoke, request.getResponseId());
        ObjectWriter.write(outputStream, serialize, response);
    }

    @Override
    public Object invoke(RPCRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object o = provider.getProvider(request.getInterfaceName());
        Method method = o.getClass().getMethod(request.getMethodName(), request.getArgsType());
        return  method.invoke(o, request.getArgs());
    }
}
