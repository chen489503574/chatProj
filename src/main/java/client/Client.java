package client;

import common.Receive;
import common.Send;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 创建客户端：发送数据+接收数据
 * 写出数据：输出流
 * 读取数据：输入流
 * 输入流 与 输出流 在同一个线程内  应该   独立处理，彼此独立
 * Created by Chenjf on 2018/11/2.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("请输入名称：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if(name.equals("")){
            return;
        }
        //创建了一个客户端
        Socket client = new Socket("172.16.5.152", 9999);

        //发送线程：启用线程，将客户端发送
        new Thread(new Send(client,name)).start();

        //接收服务器的响应的线程
        new Thread(new Receive(client)).start();


    }
}
