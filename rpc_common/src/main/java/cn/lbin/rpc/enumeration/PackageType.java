package cn.lbin.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PackageType {
//    响应对应的编码
    RESPONSE_TYPE(1),
//    请求对应的编码
    REQUEST_TYPE(0);
    int code;
}
