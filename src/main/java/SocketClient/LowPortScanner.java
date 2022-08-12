package SocketClient;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author Anthony Z.
 * @Date 10/8/2022
 * @Description:
 */
public class LowPortScanner {
    static String host = "www.yhcqw.com";
    private volatile static int port = 10;
    private static final Object o = new Object();

    public static void main(String[] args) {

        for(int i=0; i<8000; i++){
            new Thread(new Scanner()).start();
        }

    }

    private static class Scanner implements Runnable{

        @Override
        public void run() {
//            System.out.println("Port: " + port);
//            synchronized (o) {
                System.out.println("Port: " + port);
                try {
                    synchronized (o){
                        port++;
                    }
                    Socket socket = new Socket(host, port);
                    System.out.println("There is a server on port " + --port + " of " + host);

                    port++;
                    socket.close();
                } catch (UnknownHostException e) {
                    System.err.println(e);

                } catch (IOException e) {
                    // no server on this port
                }
//            }
        }
    }

}
