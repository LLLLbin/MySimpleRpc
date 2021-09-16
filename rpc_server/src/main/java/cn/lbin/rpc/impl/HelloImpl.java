package cn.lbin.rpc.impl;

import cn.lbin.rpc.Hello;

public class HelloImpl implements Hello {
    public void hello() {
        System.out.println("客户端实现Hello接口并调用了");
    }

    public void hello2(int i) {
        System.out.println("客户端实现Hello接口并调用了,传入参数为:"+i);
    }
}
