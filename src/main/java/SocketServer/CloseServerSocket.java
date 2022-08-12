package SocketServer;

import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

/**
 * @Author Anthony Z.
 * @Date 12/8/2022
 * @Description:
 *
 * Closing a ServerSocket should not be confused with
 * closing a Socket. Closing a ServerSocket frees a port on
 * the local host, allowing another server to bind the port.
 * It also breaks all currently open sockets that the
 * ServerSocket has accepted.
 */
public class CloseServerSocket {

    public static void main(String[] args) throws IOException {
        try(ServerSocket server = new ServerSocket()){

        }

    }
}
