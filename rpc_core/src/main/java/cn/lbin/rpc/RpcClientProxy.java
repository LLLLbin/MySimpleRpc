package cn.lbin.rpc;

import cn.lbin.rpc.client.CommonClient;
import cn.lbin.rpc.client.JdkClient;
import cn.lbin.rpc.entity.RPCRequest;
import cn.lbin.rpc.entity.RPCResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

@AllArgsConstructor
public class RpcClientProxy implements InvocationHandler {

    private CommonClient client;

    public RpcClientProxy() {
        this.client = new JdkClient();
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = RPCRequest.builder()
                .responseId(UUID.randomUUID().toString())
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .args(args)
                .argsType(method.getParameterTypes())
                .build();
        RPCResponse response = client.sendRequest(request);
        return response.getData();
    }
}
