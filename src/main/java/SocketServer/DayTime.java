package SocketServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @Author Anthony Z.
 * @Date 12/8/2022
 * @Description:
 *
 * Exceptions within the scope of a particular connection
 * should close that connection, but not affect other connections
 * or shut down the socket.
 *
 * Exceptions outside the scope of an individual reqiesy
 * probably should shut down the server.
 *
 * Nesting the try blocks may make the code somewhat convoluted.
 *
 * The command for stopping a program manually depends on your
 * system, under Unix, Windows, Ctrl-C will do.
 * If you are running the server in the background on a Unix system,
 * stop it by finding the server's process ID and killing it with
 * kill command.
 */
public class DayTime {

    final static int PORT = 13;
    final static String CRLF = "\r\n";

    public static void main(String[] args) {
        // try with resources to autoclose the socket
        // 里面那个try也可以使用
        try(ServerSocket server = new ServerSocket(13)){
            while(true){
                Socket connection = null;
                try{
                    connection = server.accept();
                    OutputStream out = connection.getOutputStream();
                    Writer writer = new OutputStreamWriter(out);
                    Date now = new Date();
                    writer.write(now.toString() + CRLF);
                    writer.flush();


                }catch(IOException e){

                }finally {
                    if(connection != null)
                        connection.close();
                }
            }
        }catch (IOException e){
            System.err.println(e);
        }
    }
}
