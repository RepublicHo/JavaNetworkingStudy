package SocketStudy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Anthony Z.
 * @Date 28/7/2022
 * @Description:
 */
public class Server1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket connection = serverSocket.accept();

    }
}
