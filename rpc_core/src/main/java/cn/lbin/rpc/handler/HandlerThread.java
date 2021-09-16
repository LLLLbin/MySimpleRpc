package cn.lbin.rpc.handler;

import cn.lbin.rpc.serializer.CommonSerialize;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

@AllArgsConstructor
public class HandlerThread implements Runnable{
    private Socket socket;
    private CommonSerialize commonSerialize;
    private CommonHandler handler;


    @Override
    public void run() {
        try {
            handler.response(socket,commonSerialize);
        } catch (IOException | NoSuchMethodException |
                IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
