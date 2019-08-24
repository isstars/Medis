package myRedis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            //循环内处理用户请求
            while (true){
                //调用accept方法，返回一个建立好连接的TCP Socket
                try (Socket socket = serverSocket.accept()){
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    Command command = null;
                    while (true){
                        try {
                            //命令的读取
                            command = Protocol.readCommand(is);
                            command.run(os);
                        }catch (Exception e){
                            e.printStackTrace();
                            Protocol.writeError(os,"undefined");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server().run(6379);
    }
}
