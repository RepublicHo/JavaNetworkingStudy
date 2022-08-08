package SocketStudy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author Anthony Z.
 * @Date 28/7/2022
 * @Description:
 */
public class Client1 {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(), 999);
        // get the outputStream related to the socket.
        socket.getOutputStream();
    }
}
