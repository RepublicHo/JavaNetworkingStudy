package SecureSocket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

/**
 * @Author Anthony Z.
 * @Date 12/8/2022
 * @Description:
 */
public class HTTPSClient {
    final static int PORT = 443; // default https port
    final static String CRLF = "\r\n";

    public static void main(String[] args) {
        if(args.length == 0){
            return;
        }

        String host = "www.zaobao.com";

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = null;

        try{
            socket = (SSLSocket) factory.createSocket(host, PORT);

            // enable all the suites
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);

            Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            // https requires the full URL in the GET line
            writer.write("GET http://" + host + "/ HTTP/1.1" + CRLF);
            writer.write("Host: " + host + CRLF);
            writer.write("Connection: keep-alive" + CRLF);
            writer.write("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36" + CRLF);
            writer.write(CRLF);
            writer.flush();

            // read response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // read the header
            String headerLine;
            while(!(headerLine = in.readLine()).isEmpty()){
                System.out.println(headerLine);
            }
            System.out.println();

            // read the length
            String contentLength = in.readLine();
            int length = Integer.MAX_VALUE;

            try{
                length = Integer.parseInt(contentLength.trim(), 16);

            }catch (NumberFormatException e){
                // The server doesn't send the
                // content-length in the first line of
                // the response body

            }
            System.out.println(contentLength);

            int c;
            int i = 0;
            while((c = in.read()) != -1 && i++<length){
                System.out.println(c);
            }
            System.out.println();

        }catch (IOException e){
            System.err.println(e);
        }finally{
            try{
                if(socket != null){
                    socket.close();
                }
            }catch (IOException e){

            }
        }


    }
}
