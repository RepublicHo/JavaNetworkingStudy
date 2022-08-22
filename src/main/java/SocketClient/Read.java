package SocketClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author Anthony Z.
 * @Date 9/8/2022
 * @Description:
 *
 * 在读取网络数据流的时候，可以通过先用InputStream获取字节流、InputStreamReader将字节流转化成字符流、BufferedReader将字符流以缓存形式输出的方式来快速获取网络数据流。
 *
 * 获取字符流后，可直接缓存，然后从缓存区取出，这时的速度比InputStreamReader又将快上很多。
 */
public class Read {
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("time.nist.gov", 13);
            // Another way
//            socket = new Socket();
//            SocketAddress address = new InetSocketAddress("time.nist.gov", 13);
//            socket.connect(address);

            // set timeout is optional but highly recommended.
            socket.setSoTimeout(15000); // Timeouts are measured in milliseconds.

            // Once you have opened the socket and
            // set its timeout, call getInputStream()
            // to return an InputStream you can use to
            // read bytes from the socket.
            InputStream in = socket.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");
            for(int c = reader.read(); c != -1; c = reader.read()){
                stringBuilder.append((char)c);
            }
            System.out.println(stringBuilder);
//            System.out.println(parseDate);

        }catch (IOException e){
            System.err.println(e);
        }finally {
            try{
                socket.close();
            }catch (Exception e){

            }
        }

    }

//    static Date parseDate(String s) throws ParseException{
//        String[] pieces = s.split(" ");
//        String dataTime = pieces[1] + " " + pieces[2] + "UTC";
//        DataFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
//        return format
//    }
}
