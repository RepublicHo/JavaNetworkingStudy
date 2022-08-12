package SocketClient;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author Anthony Z.
 * @Date 11/8/2022
 * @Description: Getting information about a socket.
 *
 * Socket objects have several properties that are
 * accessible through getter methods.
 *
 * Remote address  -- getInetAddress
 * Remote port     -- getPort
 * The two above tell you the remote host and port
 * the Socket is connected to, or, if the connection is
 * not closed, which host and port the Socket was connected to
 * when it was connected.
 *
 * Local address   -- getLocalAddress
 * Local port      -- getLocalPort
 * The two above tell you the network interface and port the Socket
 * is connected from.
 *
 * Each connection is assigned a differet local port, regardless of the
 * remote host. The local port assigned to any connection is unpredictable
 * and depends mostly on what other ports are in use.
 *
 * The connection to login.ibiblio.org failed because machine
 * does not run any server on port 80.
 *
 */
public class GetInfo {
    static String[] strings = new String[]{
            "www.oreilly.com",
            "www.oreilly.com",
            "www.elharo.com",
            "www.login.ibiblio.org",
            "www.google.com",
            "www.polyu.edu.hk",
            "www.yhcqw.com"

    };
    public static void main(String[] args) {

        for(String host : strings) {
            try {
                Socket socket = new Socket(host, 80);
                System.out.println("Connected to " + socket.getInetAddress() +
                        " on port " + socket.getPort() + " from port " +
                        socket.getLocalPort() + " of " + socket.getLocalAddress());

            } catch (UnknownHostException e) {
                System.err.println("Cannot connect to " + host);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
