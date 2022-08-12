package SocketClient;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author Anthony Z.
 * @Date 10/8/2022
 * @Description:
 */
public class Socket1 {
    public static void main(String[] args) {
        try(Socket toOReilly = new Socket("www.oreilly.com", 80)){
            // If the domain name server cannot resolve the hostname
            // or is not functioning, the constructor throws an
            // UnK\knownHostException
            //
        }catch (UnknownHostException e){
            System.err.println(e);
        }catch (IOException e){
            System.err.println(e);
        }

    }
}
