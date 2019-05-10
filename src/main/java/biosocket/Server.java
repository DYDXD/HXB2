package biosocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 17:08 2018/6/9
 * @Modified By:
 */
public class Server {
    private static int PORT = 8379;
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            /*服务器监听端口8379*/
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器端启动了....");
            //进行阻塞
            //启动一个线程来处理客户端请求

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket = null;
        }
    }
}

