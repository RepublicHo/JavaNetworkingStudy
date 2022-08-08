package URLConnectionStudy;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author Anthony Z.
 * @Date 8/8/2022
 * @Description:
 */
public class SourceViewer2 {

    public static void main(String[] args) {
        if(args.length > 0){
            try{
                //Open the URLConnection for reading
                URL u = new URL(args[0]);
                URLConnection uc = u.openConnection();
                try(InputStream raw = uc.getInputStream()){ // auto close
                    InputStream buffer = new BufferedInputStream(raw);
                    // chain the InputStream to a reader
                    Reader reader = new InputStreamReader(buffer);
                    int c;
                    while((c = reader.read()) != -1){
                        System.out.println((char) c);
                    }
                }

            }catch (MalformedURLException e){
                System.err.println(args[0] + " is not a parseable URL");
            }catch (IOException e){
                System.err.println(e);
            }

        }
    }
}
