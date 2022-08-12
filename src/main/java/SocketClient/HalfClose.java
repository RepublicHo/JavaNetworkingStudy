package SocketClient;

import java.io.*;
import java.net.Socket;

/**
 * @Author Anthony Z.
 * @Date 10/8/2022
 * @Description: For half-closed sockets
 * shutdownInput()
 * shutdownOutput()
 *
 * Neither actually close the socket. Instead, they
 * just adjust the stream connected to the socket.
 *
 * Further reads from the input stream after shutting
 * down input return -1. And Further writes to the socket after
 * shutting down output throw IOException.
 */
public class HalfClose {
    public static void main(String[] args) {
        try(Socket connection = new Socket("www.oreilly.com", 80)){
            Writer out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
            out.write("GET / HTTP 1.0\r\n\r\n");
            out.flush();
            connection.shutdownOutput();
            // read the response...

            InputStream in = connection.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");
            for(int c = reader.read(); c != -1; c = reader.read()){
                stringBuilder.append((char)c);
            }
            System.out.println(stringBuilder);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
