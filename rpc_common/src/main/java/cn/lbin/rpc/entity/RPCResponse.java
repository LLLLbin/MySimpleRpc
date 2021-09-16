package cn.lbin.rpc.entity;

import cn.lbin.rpc.enumeration.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class RPCResponse<T> implements Serializable {

    /**
     * 对应的请求号
     */
    private String requestId;

    /**
     * 响应的数据
     */
    private T data;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应状态码
     */
    private Integer statusCode;

    public static <T> RPCResponse<T> success(T data, String requestId) {
        RPCResponse<T> response = new RPCResponse<T>();
        response.setStatusCode(200);
        response.setMessage("调用成功");
        response.setData(data);
        response.setRequestId(requestId);
        return response;
    }

    public static RPCResponse failed(ResponseCode code, String requestId) {
        RPCResponse response = new RPCResponse();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMsg());
        response.setRequestId(requestId);
        return response;
    }
}
