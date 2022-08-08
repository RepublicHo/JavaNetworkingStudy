package ThreadsStudy;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DataTruncation;

/**
 * @Author Anthony Z.
 * @Date 25/7/2022
 * @Description:
 * If you subclass Thread, you should override run() and nothing else!!
 *
 * One way to avoid overriding the standard Thread methods
 * is not to subclass Thread. Instead, implement Runnable
 */
public class DigestThread implements Runnable{
    private String fileName;
    public DigestThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try{
            FileInputStream in = new FileInputStream(fileName);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);

            while(din.read() != -1);
            din.close();
            byte[] digest = sha.digest();

            StringBuilder result = new StringBuilder(fileName);
            result.append(": ");
            result.append(DatatypeConverter.printHexBinary(digest));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String[] filesName = new String[]{"abc.txt"};
        for(String filename:filesName){
            Thread t = new Thread(new DigestThread(filename));
            t.start();
        }
    }
}
