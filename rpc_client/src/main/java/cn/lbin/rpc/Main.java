package cn.lbin.rpc;

public class Main {
    public static void main(String[] args) {
        Hello hello = new RpcClientProxy().getProxy(Hello.class);
        hello.hello2(6);
    }
}
