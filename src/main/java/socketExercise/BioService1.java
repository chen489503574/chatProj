package socketExercise;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Chenjf on 2018/11/1.
 */
public class BioService1 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10101);
            System.out.println("----------服务器启动了----------");
            while (true){
                //获取一个套接字
                Socket socket = serverSocket.accept();
                if(socket!=null){
                    System.out.println("来了一个新客户端");
                    //业务处理
                    handler(socket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 进行业务处理，读取数据
     * @param socket
     *
     */
    private static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true){
                //读取数据   阻塞
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println("这是客户端发来的信息："+new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("socket关闭");
            try {
                socket.close();
                socket=null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
