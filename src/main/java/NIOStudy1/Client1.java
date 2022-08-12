package NIOStudy1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @Author Anthony Z.
 * @Date 10/8/2022
 * @Description:
 */
public class Client1 {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8080));
        System.out.println("waiting...");
    }
}
