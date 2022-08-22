package UDPStudy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author Anthony Z.
 * @Date 15/8/2022
 * @Description: retrieve the same data from NIST using UDP.
 */
public class Client1 {
    final static int PORT = 13;
    final static String HOSTNAME = "time.nist.gov";
    public static void main(String[] args) {

        // First open a socket on port 0
        // it's quite different from TCP. we only need to specify
        // a local port to connect to.
        // The socket does not know the remote host or address.
        // By specifying port 0, we ask Java to pick a
        // random port for us.
        try(DatagramSocket socket = new DatagramSocket(0)){
            socket.setSoTimeout(100000);
            // Timeouts are even more important for UDP than TCP
            // because many problem that would cause an IOException
            // in TCP silently fail in UDP.
            // Say if the remote host is not listening on the
            // targeted port, we will never hear about it.

            InetAddress host = InetAddress.getByName(HOSTNAME);
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host, PORT);
            DatagramPacket response = new DatagramPacket(new byte[10024], 10024);
            socket.send(request);
            socket.receive(response);
            String result = new String(response.getData(), 0, response.getLength(), "US-ASCII");
            System.out.println(result);
        }catch (IOException e){
            System.err.println(e);
        }
    }
}
