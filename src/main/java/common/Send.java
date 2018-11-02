package common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 发送数据  线程
 * Created by Chenjf on 2018/11/2.
 */
public class Send implements Runnable {

    //控制台输入流
    private BufferedReader console;
    //管道输出流
    private DataOutputStream dos;
    //控制线程
    private boolean isRunning = true;
    //名称
    private String name ;

    public Send() {
        this.console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket client, String name) throws IOException {
        this();
        try {
            dos = new DataOutputStream(client.getOutputStream());
            this.name = name;
            send(this.name);
        }catch (IOException e){
            isRunning = false;
            CloseUtil.closeAll(dos,console);
        }
    }

    //1、从控制台接收数据
    private String getMsgFromConsole(){
        try {
            return console.readLine();
        }catch (IOException e ){

        }
        return "";
    }
    //2、发送数据(接收完数据后)
    private void send(String msg) {
            try {
                if(null!=msg && !msg.equals("")) {
                    dos.writeUTF(msg);
                    dos.flush();//强制刷新
                }
            } catch (IOException e) {//出现异常关闭有所的流
                isRunning = false;
                CloseUtil.closeAll(console,dos);
            }

    }

    public void run() {
        //线程体
        while (isRunning){
            send(getMsgFromConsole());
        }
    }
}
