package SocketServer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Anthony Z.
 * @Date 12/8/2022
 * @Description: Multithreaded server
 *
 * A thread places a far smaller load on the server than
 * a child process.
 *
 * In my last project, the program spawns a new thread
 * for each connection, numerous roughly simultaneous
 * incoming connections can cause it to spawn an
 * indefinite number of threads. Eventually, the java
 * virtual machine will run out of memory and crash.
 *
 * A better way is to use a fixed thread pool
 * to limit the potential resource usage.
 * Fifty threads should be plenty.
 *
 * It shouldn't crash no matter what load it's
 * under. It may start refusing connections.
 * But it won't crash.
 */
public class DayTime2 {
    final static int PORT = 13;
    final static String CRLF = "\r\n";

    /**
     * Rater than starting threads
     * it submits these callables to an
     * executor service preconfigured with
     * 50 threads.
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        try(ServerSocket server = new ServerSocket(PORT)){
            while(true){
                try{
                    Socket connection = server.accept();
                    Callable<Void> task = new DaytimeTask(connection);
                    pool.submit(task);
                }catch (IOException e){}

            }
        }catch (IOException e){
            System.err.println(e);
        }


    }

    private static class DaytimeTask implements Callable<Void>{
        private Socket connection;
        DaytimeTask(Socket connection){
            this.connection = connection;
        }

        @Override
        public Void call() throws Exception {
            try{
                Writer writer = new OutputStreamWriter(connection.getOutputStream());
                Date date = new Date();
                writer.write(date.toString() + CRLF);
                writer.flush();


            }catch (IOException e){
                System.err.println(e);
            }finally {
                try{
                    connection.close();
                }catch (IOException e){
                    // ignore
                }

            }
            return null;
        }
    }
}
