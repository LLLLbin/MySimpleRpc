package cn.lbin.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS(200, "调用成功"),
    FAILED(500, "调用失败"),
    METHOD_NOT_FOUND(500, "找不到方法"),
    CLASS_NOT_FOUND(500, "找不到类");

    private Integer code;
    private String msg;

}
