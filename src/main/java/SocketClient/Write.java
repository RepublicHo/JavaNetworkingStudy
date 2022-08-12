package SocketClient;

import java.io.*;
import java.net.Socket;

/**
 * @Author Anthony Z.
 * @Date 10/8/2022
 * @Description: Although it's possible to send data over
 * the socket using output stream at the same time you're
 * reading data over the input stream, most protocol are
 * designed so that the client is either reading or writing
 * over a socket.
 *
 * The following example shows a complete dict client.
 * It connects dict.org, and translates any words the user
 * enters on the String into another language, say Latin.
 *
 *
 */
public class Write {
    public static final String SERVER = "dict.org";
    public static final int PORT = 2628;
    public static final int TIMEOUT = 15000;

    private static final String CRLF = "\r\n";

    private static final String[] words = new String[]{
            "gold", "uranium"
    };

    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket(SERVER, PORT);
            // Another way
//            socket = new Socket();
//            SocketAddress address = new InetSocketAddress("time.nist.gov", 13);
//            socket.connect(address);
            // set timeout is optional but highly recommended.
            socket.setSoTimeout(TIMEOUT); // Timeouts are measured in milliseconds.

            // In the dict protocol, the client speaks first,
            // so ask for the output stream using getOutputStream()
            // This method returns a raw OutputStream for writing data
            // from your application to the other end of the socket.
            OutputStream out = socket.getOutputStream();

            Writer writer = new OutputStreamWriter(out, "UTF-8");
            writer = new BufferedWriter(writer);

            // InputStream and BufferedReader are used for
            // getting the socket's input stream
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in, "UTF-8"));
            for(String word : words){
                System.out.println("1");
                define(word, writer, reader);
                System.out.println(2);
            }

            writer.write("quit" + CRLF);
            writer.flush();


        }catch (IOException e){
            System.err.println(e);
        }finally {
            if(socket != null){
                try{
                    socket.close();
                }catch (IOException e){
                    // ignore
                }
            }
        }
    }

    static void define(String word, Writer writer, BufferedReader reader) throws IOException {
        // write the command over the socket
        writer.write("DEFINE eng-zh " + word + CRLF);
        // Flush the output and we'll be sure the command is sent
        // over the network.
        writer.flush();
        System.out.println("tets1");

        // The server should now respond with a definition.
        // We can read that using the socket's input stream.
//        550 Invalid database, use "SHOW DB" for list of databases
//        552 No match
//        150 n definitions retrieved - definitions follow
//        151 word database name - text follows
//        250 ok (optional timing information here)
        for(String line = reader.readLine(); line != null; line = reader.readLine()){
            System.out.println(line);
            if(line.startsWith("250 ")){ //OK
                System.out.println("test2");
                continue;
            }else if(line.startsWith("552 ")){ // no match
                System.out.println("test3");
                System.out.println("No definition found for " + word);
                return;
            }
            else if(line.matches("\\d\\d\\d .*")){
                System.out.println("test4"); continue;}
            else if(line.trim().equals(".")) {
                System.out.println("test5");continue;
            }
            else System.out.println(line);
        }
    }
}
