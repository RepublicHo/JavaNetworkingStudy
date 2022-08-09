package InternetAddressStudy;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author Anthony Z.
 * @Date 9/8/2022
 * @Description:
 * getByName() method actually makes a connection to
 * the local DNS server to look up the name and the
 * numeric address.
 *
 * If you have looked up this local host previously,
 * the information may be cached locally,
 * in which case a networking connection is not required.
 *
 * If DNS server cannot find the address, this method
 * throws an UnknownHostException, a subclass of IOException.
 */
public class OReillyByName {
    public static void main(String[] args) {
        try{
            InetAddress address = InetAddress.getByName("www.google.com"); // 不要带HTTP//
            System.out.println(address);

            InetAddress address1 = InetAddress.getByName("142.250.66.132");
            //
            System.out.println(address1);

            // If we are not connected to the Internet, and the
            // system does not have a fixed IP address or domain name,
            // we'll probably see localhost as domain name and 127.0.0.1 as the IP address
            System.out.println("Local host:" + InetAddress.getLocalHost());

            // Some actually has two addresses.
            InetAddress[] inetAddresses = InetAddress.getAllByName("www.ltn.com.tw");
            for(InetAddress inetAddress: inetAddresses){
                System.out.println(inetAddress);
            }
        }catch (UnknownHostException e){
            System.err.println("Could not find the address");
        }
    }
}
