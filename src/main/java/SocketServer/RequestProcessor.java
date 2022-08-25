package SocketServer;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @Author Anthony Z.
 * @Date 23/8/2022
 * @Description:
 */
public class RequestProcessor implements Runnable{

    private static Logger logger = Logger.getLogger(JHTTP.class.getCanonicalName());
    private File rootDirectory;
    private String indexFileName = "index.html";
    private Socket connection;
    public RequestProcessor(File rootDirectory, String indexFileName, Socket connection){
        if(rootDirectory.isFile()){
            throw new IllegalArgumentException("rootDirectory must be a directory, not a file");
        }

        try{
            rootDirectory = rootDirectory.getCanonicalFile();
        }catch (IOException ex){

        }

        this.rootDirectory = rootDirectory;

        if(indexFileName != null){
            this.indexFileName = indexFileName;
        }

        this.connection = connection;
    }

    @Override
    public void run() {

        // For security checks
        String root = rootDirectory.getPath();
        try{
            // Chain a Writer to the socket's output stream
            Writer out = new BufferedWriter(
                    new OutputStreamWriter(
                            connection.getOutputStream(), "US-ASCII"
                    )
            );

            // Chain a Reader to the socket's input stream
            Reader in = new InputStreamReader(
                    new BufferedInputStream(
                            connection.getInputStream()
                    ), "US-ASCII"
            );
        }catch (IOException ex){

        }
    }

    private void sendHeader(){

    }
}
