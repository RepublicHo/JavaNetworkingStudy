package SocketServer;


import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Anthony Z.
 * @Date 23/8/2022
 * @Description: Full-blown HTTP / Full-Fledged HTTP Server called JHTTP
 *
 * Because this server may have to read and serve large files from
 *  the filesystem over potentially slow network connections.
 * Rather than processing each request as it arrives in the main
 * thread of execution, we'll place incoming connections in a pool.
 *
 */
public class JHTTP {
    private static Logger logger = Logger.getLogger(JHTTP.class.getCanonicalName());
    private static final int NUM_THREADS = 50;
    private static final String INDEX_FILE = "index.html";

    private final File rootDirectory;
    private final int port;
    public JHTTP(File rootDirectory, int port) throws IOException{
        if(!rootDirectory.isDirectory()){
            throw new IOException(rootDirectory + " does not exist as a directory");
        }
        this.rootDirectory = rootDirectory;
        this.port = port;
    }

    public void start() throws IOException{
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

        try(ServerSocket server = new ServerSocket(port)){
            logger.info("Accepting connections on port " + server.getLocalPort());
            logger.info("Document Root: " + rootDirectory);

            while(true){
                try{
                    Socket request = server.accept();
                    Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, request);
                    pool.submit(r);
                }catch (IOException ex){
                    logger.log(Level.WARNING, "Error accepting connection", ex);
                }

            }
        }
    }

    public static void main(String[] args) {

        // Get the Document root
        File docRoot = null;

        try{
            docRoot = new File(args[0]);
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Usage: java JHTTP docRoot port");
        }

        // set the port to listen on
        int port;
        try{
            port = Integer.parseInt(args[1]);
            if(port<0 || port>65535){
                port = 80;
            }
        }catch (RuntimeException ex){
            port = 80;
        }

        try{
            JHTTP webserver = new JHTTP(docRoot, port);
            webserver.start();
        }catch (IOException ex){

        }

    }
}
