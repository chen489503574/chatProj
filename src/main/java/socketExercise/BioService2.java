package socketExercise;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Chenjf on 2018/11/1.
 */
public class BioService2 {
    public static void main(String[] args) throws IOException {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(10101);
        System.out.println("-----------服务器启动了----------");
        while (true){
            //获取一个套接字(阻塞)
            final Socket socket = serverSocket.accept();
            if(socket!=null){
                System.out.println("来了一个客户端！");
            }
            newCachedThreadPool.submit(new Runnable() {
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    /**
     * 业务处理，读取数据
     * @param socket
     */
    private static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                //读取数据  阻塞
                int read = inputStream.read(bytes);
                if (read!=-1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("socket关闭");
            try {
                socket.close();
                socket= null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
