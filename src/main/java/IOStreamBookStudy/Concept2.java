package IOStreamBookStudy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author Anthony Z.
 * @Date 25/7/2022
 * @Description:
 * Finally, when we are done with a stream, close it by invoking its close() method. Failure to close a stream
 * in a long-running program can leak file handlers, network ports, and other resources.
 */
public class Concept2 {
    void java6(){
        OutputStream out = null;
        try{
            out = new FileOutputStream("/src/resource/123.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(out != null){
                try{
                    out.close();
                }catch (IOException ex){

                }
            }
        }
    }

    /**
     * The finally clause is no longer needed. Java automatically invokes close()
     * on any AutoCloseable objects declared inside the argument list of the try block.
     */
    void java7(){
        try(OutputStream out = new FileOutputStream("/src/java/resources/123.txt")) {
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }
}
