package SocketServer;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Anthony Z.
 * @Date 22/8/2022
 * @Description:
 *
 * In this section, we develop a server that redirects users
 * from one website to another, say from cnet.com to
 * www.cnet.com.
 */
public class Redirector {

    private static final Logger logger = Logger.getLogger("Redirector");
    private final int port;
    private final String newSite;
    final static String CRLF = "\r\n";

    public Redirector(int port, String newSite){
        this.port = port;
        this.newSite = newSite;
    }

    public void start(){
        // 1. Bind the server socket to the port
        try(ServerSocket server = new ServerSocket(this.port)){
            // 2. print a brief status message
            logger.info("Redirecting connection on port " +
                    server.getLocalPort() + " to " + newSite);
            // 3. Enter an infinite loop in which it listens for connections
            while(true){
                try{

                    // Every time a connection is accepted,
                    // the resulting Socket object is used to
                    // construct a RedirectThread.
                    Socket socket = server.accept();
                    new Thread(new RedirectThread(socket)).start();

                    // All further interaction with the client
                    // takes place in this new thread.
                    //
                    // The start() method then simply waits for the next
                    // incoming connection.
                }catch (IOException ex){
                    logger.warning("Exception accepting connection");
                }catch (RuntimeException ex){
                    logger.log(Level.SEVERE, "Unexpected error", ex);
                }
            }
        }catch (BindException ex){
            logger.log(Level.SEVERE, "Couldn't start server", ex);

        }catch (IOException ex){
            logger.log(Level.SEVERE, "Error opening server socket", ex);
        }
    }
    private class RedirectThread implements Runnable{

        private final Socket connection;
        RedirectThread(Socket s){
            this.connection = s;
        }
        @Override
        public void run() {
            try {
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
                        )
                );

                // Read the first line only, that's all
                // we need
                // GET /directory/filename.html HTTP/1.0
                // It's possible that the first word would be
                // POST or PUT instead or there will be no HTTP version.
                // But this must begin with a slash.
                StringBuilder request = new StringBuilder(80);
                while(true){

                    int c = in.read();
                    if(c == '\r' || c == '\n' || c == -1){
                        break;
                    }
                    request.append((char)c);
                }

                // To handle a request like aforementioned,

                String get = request.toString();
                String[] pieces = get.split("\\w*");
                String theFile = pieces[1];

                // if this is HTTP/1.0 or later
                // send a MIME header
                // MIME headers are not used for old browsers
                // that do not understand HTTP/1.0
                if(get.indexOf("HTTP") != -1){
                    out.write("HTTP/1.0 302 FOUND" + CRLF);
                    Date now = new Date();
                    out.write("Date: " + now + CRLF);
                    out.write("Server: Redirector 1.1" + CRLF);
                    out.write("Location: " + newSite + theFile + CRLF);
                    out.write("Content-type: text/html/" + CRLF + CRLF);
                    out.flush();
                    // Everything after this will be HTML,
                    // which is processed by the browser and
                    // displayed to the user.
                }
                // Not all browsers support redirection
                // so those users can manually jump to the new site.                 // so we need to produce HTML that says
                // where the document has moved to
                out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>" + CRLF);
                out.write("<BODY><H1>Document moved</H1>" + CRLF);
                out.write("The document " + theFile +
                        " has moved to" + CRLF +
                        "<A HREF=\"" + newSite + theFile + "\">" +
                        newSite + theFile + "</A>." + CRLF +
                        "Please update your bookmarks<P>");

                out.write("</BODY></HTML>" + CRLF);
                out.flush();
                logger.log(Level.INFO, "Redirecred" + connection.getRemoteSocketAddress());

            }catch (IOException ex){
                logger.log(Level.WARNING, "Error talking to " +
                        connection.getRemoteSocketAddress(), ex);
            }finally {
                try{
                    connection.close();
                }catch (IOException ex){

                }

            }


        }
    }

    public static void main(String[] args) {
        int thePort;
        String theSite;
        try{
            theSite = args[0];
            if(theSite.endsWith("/")){
                theSite = theSite.substring(0, theSite.length() - 1);

            }
        }catch (RuntimeException ex){
            // If the site is omitted, Redirector prints an error message
            // and exits.
            System.out.println("Usage: Java Redirector http://www.newsite.com/ port");
            return;
        }

        try{
            thePort = Integer.parseInt(args[1]);

        }catch (RuntimeException ex){
            thePort = 80;
        }

        Redirector redirector = new Redirector(thePort, theSite);

    }
}
