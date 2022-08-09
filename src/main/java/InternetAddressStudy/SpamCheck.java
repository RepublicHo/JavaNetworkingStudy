package InternetAddressStudy;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author Anthony Z.
 * @Date 9/8/2022
 * @Description:
 *
 * To find out if a certain IP addresses is a known spammer, reverse the
 * bytes of the address. add the blackhole service, and look it up. If
 * the address is found, it's a spammer. If it isn't, it's not.
 */
public class SpamCheck {
    public static final String BLACKHOLE = "sbl.spamhaus.org";
    public static void main(String[] args) {
        String[] IPAddresses = new String[]{"www.mail.ctgoodjobsalert.hk"};
        for(String s:IPAddresses){
            if(isSpammer(s)){
                System.out.println("True");
            }else{
                System.out.println("False");
            }
        }
    }
    private static boolean isSpammer(String str){
        try{
            InetAddress address = InetAddress.getByName(str);
            byte[] quad = address.getAddress();
            String query = BLACKHOLE;
            for(byte octet : quad){
                int unsignedByte = octet < 0 ? octet + 256: octet;
                query = unsignedByte + query;
            }
            InetAddress.getByName(query);
            return true;
        }catch(UnknownHostException e){
            return false;
        }
    }


}
