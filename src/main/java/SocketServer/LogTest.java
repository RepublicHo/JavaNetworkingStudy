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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Anthony Z.
 * @Date 22/8/2022
 * @Description:
 *
 * Loggers are thread safe, so there is no problem
 * storing them in a shared static field. Indeed,
 * they have to be because even if the Logger object
 * were not shared between threads, the logfile
 * or database would be. This in important in
 * multithreaded servers.
 *
 * By default, the logs are just output to the console.
 * 怎么写到文件自己以后在研究
 */
public class LogTest {
    public final static int PORT = 13;
    private final static Logger auditLogger = Logger.getLogger("requests");
    private final static Logger errorLogger = Logger.getLogger("errors");
    final static String CRLF = "\r\n";

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        try(ServerSocket server = new ServerSocket(PORT)){
            while(true){
                try{
                    Socket connection = server.accept();
                    Callable<Void> task = new LogTest.DaytimeTask(connection);
                    pool.submit(task);
                }catch (IOException ex){
                    // Once you have a logger, we can write to it using any of several methods
                    // The most basic is log
                    errorLogger.log(Level.SEVERE, "accept error", ex);
                }catch (RuntimeException ex){
                    errorLogger.log(Level.SEVERE, "unexpected error " + ex.getMessage(), ex);
                }

            }
        }catch (IOException ex){
            errorLogger.log(Level.SEVERE, "Couldn't start server", ex);
        }catch (RuntimeException ex){
            errorLogger.log(Level.SEVERE, "Couldn't start server" + ex.getMessage(), ex);
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
