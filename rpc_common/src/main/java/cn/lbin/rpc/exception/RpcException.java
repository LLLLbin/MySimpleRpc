package cn.lbin.rpc.exception;

import cn.lbin.rpc.enumeration.RpcError;

public class RpcException extends RuntimeException {
    private String msg;
    private RpcError error;
    public RpcException(RpcError error) {
        super(error.getMsg());
    }

    public RpcException(RpcError error,String detail){
        super(error.getMsg()+":"+detail);
    }



}
