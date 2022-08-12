package SocketServer;

/**
 * @Author Anthony Z.
 * @Date 12/8/2022
 * @Description:
 */
public class EchoServer {
    final static int DEFAULT_PORT = 7;

    public static void main(String[] args) {
        int port;
        try{
            port = Integer.parseInt(args[0]);
        }catch (RuntimeException e){ // 不明白为什么要用RuntimeException
            port = DEFAULT_PORT;
        }

        System.out.println("Listening for connection " +
                "on port " + port);


    }
}
