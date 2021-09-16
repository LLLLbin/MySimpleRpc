package cn.lbin.rpc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RPCRequest implements Serializable {
    private String responseId;
    private String methodName;
    private Object[] args;
    private Class<?>[] argsType;
    private String interfaceName;
    private Boolean isHeadBeat;
}
