package URLConnectionStudy;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author Anthony Z.
 * @Date 8/8/2022
 * @Description:
 *
 * The URLConnection class is declared abstract. However, all but one of its method
 * are implemented. That is connect() method. \
 *
 * We rarely use connect() method.
 *
 * When a URLConnection is first constructed, it's unconnected. That is,
 * the local and remote host cannot send and retrieve data. There is no
 * socket connection between two hosts. However, getInputStream(), getContent(),
 * getHeaderField() and other methods that require an open connection will call
 * connect() is the connection is not yet opened.
 */
public class SourceViewer1 {
    public static void main(String[] args) {
        try{
            // Construct a URL object
            URL u = new URL("https://republicho.github.io/");
            // Invoke the URL object's openConnection() method
            // to retrieve a URLConnection for that URL.
            URLConnection uc = u.openConnection();

            try(InputStream raw = uc.getInputStream()){ // auto close
                InputStream buffer = new BufferedInputStream(raw);
                // chain the InputStream to a reader
                Reader reader = new InputStreamReader(buffer);
                int c;
                while((c = reader.read()) != -1){
                    System.out.print((char) c);
                }
            }
        }catch (MalformedURLException e){
            System.err.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
