package cn.lbin.rpc.client;

import cn.lbin.rpc.entity.RPCRequest;
import cn.lbin.rpc.entity.RPCResponse;
import cn.lbin.rpc.loadbalance.CommonLoadBalance;
import cn.lbin.rpc.registry.redis.RedisServiceDiscovery;
import cn.lbin.rpc.serializer.CommonSerialize;
import cn.lbin.rpc.serializer.JsonSerializer;
import cn.lbin.rpc.transport.ObjectReader;
import cn.lbin.rpc.transport.ObjectWriter;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class JdkClient implements CommonClient {
    private CommonSerialize commonSerialize;

    public JdkClient() {
        this.commonSerialize = new JsonSerializer();
    }

    public JdkClient(int serializerCode) {
        this.commonSerialize = CommonSerialize.getByCode(serializerCode);
    }

    @Override
    public RPCResponse sendRequest(RPCRequest request) throws Exception {
        InetSocketAddress address = new RedisServiceDiscovery().getInetByInterfaceName(request.getInterfaceName());
        Socket socket = new Socket(address.getAddress().getHostAddress(), address.getPort());
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        ObjectWriter.write(outputStream, commonSerialize, request);
        /*传输结束后开始等待接收服务端响应的数据*/
        RPCResponse response;
        response = (RPCResponse) ObjectReader.read(inputStream);
        return response;
    }
}
