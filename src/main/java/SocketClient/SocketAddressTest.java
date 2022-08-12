package SocketClient;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author Anthony Z.
 * @Date 11/8/2022
 * @Description:
 */
public class SocketAddressTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("www.yahoo.com", 80);
        SocketAddress yahooLocal = socket.getLocalSocketAddress();
        SocketAddress yahooRemote = socket.getRemoteSocketAddress();


        System.out.println(yahooRemote);
        socket.close();

        Socket socket1 = new Socket();
        socket1.connect(yahooRemote);


    }
}
