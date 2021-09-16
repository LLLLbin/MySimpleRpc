package cn.lbin.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoadBalance {
    //
    RANDOM(0);
    private int code;
}
