package IOStudy;

import java.io.*;

/**
 * @Author Anthony Z.
 * @Date 25/8/2022
 * @Description: Filters are connected to streams by their constructors.
 */
public class ChainFilters {

    // 1. Buffer the input from the file data.txt
    static void test1() throws FileNotFoundException {

        // An FileInputStream object is created by passing the name of the
        // file as an argument to the FileInputStream constructor.
        FileInputStream fin = new FileInputStream("data.txt");

        // An BufferedInputStream object is created by ...
        BufferedInputStream bin = new BufferedInputStream(fin);

    }

    //Intermixing calls to different streams connected to the same
    //resource may violate several implicit contracts of the filter streams.
    //Most of the time, we should only use the last filter in the chain
    //to do the actual reading or writing.
    static void test2() throws FileNotFoundException {

        InputStream in = new FileInputStream("data.txt");

        // One way to write the code so that it's at least harder to
        // introduce this sort of bug is
        // to deliberately overwrite the reference to the underlying input stream.
        in = new BufferedInputStream(in);
        // After it executes, there is no longer any way to access the underlying
        // file input stream, so we cannot accidentally read from it and corrupt the
        // buffer,

        // It works because it's not necessary to distinguish between the methods
        // of InputStream and BufferedInputStream
        // given that BufferedInputStream is simply used polymorphically
        // as in instance of InputStream.
    }

    // Although these statements can get a little bit long, it's easier to split
    // the statement across several lines
    static void test3() throws FileNotFoundException{
        DataOutputStream dout = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("data.txt")
                )
        );
    }

    public static void main(String[] args) {

    }
}
