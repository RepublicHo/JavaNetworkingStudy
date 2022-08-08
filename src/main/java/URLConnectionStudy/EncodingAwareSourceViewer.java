package URLConnectionStudy;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author Anthony Z.
 * @Date 8/8/2022
 * @Description: getContentType()
 * This method returns the MIME media type of the response
 * body.
 *
 * In this case, getContentType() returns the full value of
 * the content-type field, including the character encoding.
 *
 *
 *
 */
public class EncodingAwareSourceViewer {

    public static void main(String[] args) {

        String URL = "https://republicho.github.io/";
        try{
            String encoding = "ISO-8859-1"; //HTTP default
            URL u = new URL(URL);
            URLConnection uc = u.openConnection();
            String contentType = uc.getContentType();
            System.out.println(contentType);

            int encodingStart = contentType.indexOf("charset=");
            System.out.println(encodingStart);

            if(encodingStart != -1){
                encoding = contentType.substring(encodingStart + 8);
                System.out.println(encoding);
            }

            InputStream in = new BufferedInputStream(uc.getInputStream());
            Reader r = new InputStreamReader(in, encoding);

            int c;
            while((c=r.read()) != -1){
//                System.out.print((char) c);
            }
            r.close();

        }catch (MalformedURLException e){
            System.err.println(args[0] + " is not a parsable URL. ");
        }catch (UnsupportedEncodingException e){
            System.err.println();
        } catch (IOException e) {

        }

    }
}
