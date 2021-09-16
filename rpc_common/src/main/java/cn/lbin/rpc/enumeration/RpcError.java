package cn.lbin.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RpcError {
    //
    UNKNOW_ERROR("未知异常"),
    PACKAGE_TYPE_NOT_FIND("找不到对应的传输类型"),
    MAGICNUMBER_ERROR("传输协议标识码错误"),
    SERIALIZE_NOT_FIND("找不到对应的序列化方式"),
    ILLEGAL_OBJECT("传入的对象不符合要求"),
    REGISTRY_NOT_FIND("找不到对应的注册中心"),
    INSTANCE_NOT_FIND("注册中心没有该接口实现类");
    private String msg;
}
