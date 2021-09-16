package cn.lbin.rpc;

import cn.lbin.rpc.entity.RPCResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, UnknownHostException {
        Method method = Test.class.getMethod("hello", int.class);
        method.invoke(new Test(), 2);
//        InetSocketAddress socketAddress = InetSocketAddress.createUnresolved("127.0.0.1", 9999);
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9999);
        System.out.println(socketAddress.getAddress().getHostAddress());
        System.out.println(socketAddress.getPort());
        System.out.println(socketAddress);
        System.out.println("========================================");
        System.out.println(method.getDeclaringClass().getName());
        System.out.println(Test.class.getName());
        System.out.println("========================================");
        Test test = new Test();
        byte[] bytes = test.intToByteArray(0xCAFEBABE);
        int j=bytesToInt(bytes);
        System.out.println(j);

    }

    public void hello(int i) {
        System.out.println(i);
    }

    public byte[] intToByteArray(int number) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((number >> 24) & 0xFF);
        bytes[1] = (byte) ((number >> 16) & 0xFF);
        bytes[2] = (byte) ((number >> 8) & 0xFF);
        bytes[3] = (byte) (number & 0xFF);
        return bytes;
    }


    public static int bytesToInt(byte[] src) {
        int value;
        value = ((src[0] & 0xFF)<<24)
                |((src[1] & 0xFF)<<16)
                |((src[2] & 0xFF)<<8)
                |(src[3] & 0xFF);
        return value;
    }
}
