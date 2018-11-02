package common;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 接收线程
 * Created by Chenjf on 2018/11/2.
 */
public class Receive implements Runnable {

    //输入流
    private DataInputStream dis;
    //线程标识
    private boolean isRunning = true;

    public Receive() {
    }

    public Receive(Socket client) {
        try {
            dis = new DataInputStream(client.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
    }

    /**
     * 接收数据
     */
    private String receive(){
        String msg = "";
        try {
            msg = dis.readUTF();
        }catch (IOException e ){
            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
        return msg;
    }
    public void run() {
        //线程体
        while (isRunning){
            System.out.println(receive());
        }
    }
}
