package cn.lbin.rpc.client;

import cn.lbin.rpc.entity.RPCRequest;
import cn.lbin.rpc.entity.RPCResponse;

public interface CommonClient {
    RPCResponse sendRequest(RPCRequest request) throws Exception;
}
