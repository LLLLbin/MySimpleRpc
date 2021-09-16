package cn.lbin.rpc;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import cn.lbin.rpc.handler.HandlerThread;
import cn.lbin.rpc.impl.HelloImpl;
import cn.lbin.rpc.provider.RedisServiceProvider;
import cn.lbin.rpc.serializer.CommonSerialize;
import cn.lbin.rpc.handler.JdkHandler;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ServerMain {

    public static void main(String[] args) throws Exception {
        new RedisServiceProvider().addProvider(new HelloImpl(),
                Hello.class.getName(),new InetSocketAddress("127.0.0.1",9999));
        ServerSocket serverSocket = new ServerSocket(9999);
        ExecutorService pool = ThreadUtil.newExecutor(10);
        Socket socket;
        while ((socket = serverSocket.accept()) != null){
            pool.execute(new HandlerThread(socket,CommonSerialize.getByCode(0),new JdkHandler()));
        }
    }

}
