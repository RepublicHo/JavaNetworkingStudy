package Basics;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author Anthony Z.
 * @Date 28/7/2022
 * @Description:
 */
public class InetStudy1 {
    public static void main(String[] args) throws UnknownHostException {
        // 1. 获取本机的InetAddress对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        // 2. 根据指定的主机名，获取InetAddress对象
        InetAddress localHost2 = InetAddress.getByName("LAPTOP-SB37SJAD");
        System.out.println(localHost2);

        // 3. based on the domain, return InetAdderess Object

        InetAddress googleHost = InetAddress.getByName("www.google.com");
        System.out.println(googleHost);


        // 4.based on 3, return the address
        String googleHostAddress = googleHost.getHostAddress();
        System.out.println(googleHostAddress);

        // 5. based on InetAddress object, return host name
        String googleHostName = googleHost.getHostName();
        System.out.println(googleHostName);
    }
}
