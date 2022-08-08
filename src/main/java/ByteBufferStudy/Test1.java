package ByteBufferStudy;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author Anthony Z.
 * @Date 8/8/2022
 * @Description:
 * ByteBuffer
 *
 * ByteBuffer doesn't have a length() method, instead it
 * has several length-like concepts.
 *
 *
 *
 *
 */

@Slf4j
public class Test1 {
    public static void main(String[] args) throws FileNotFoundException {

        // try .. with resource
        // it declares one or more resources
        // A resource is an object that must be closed after
        // the program is finished with it.
        try(FileChannel channel = new FileInputStream("src/main/resources/data.txt").getChannel()){
            // 准备缓存区
            ByteBuffer buffer = ByteBuffer.allocate(10);

            while(true){
                // 从channel读取数据 向buffer写入
                int len = channel.read(buffer); // 切换至读模式
                // log自己去写配置文件
                log.debug("读到的字节 {}", len);

                if(len == -1) break;

                // 打印buffer的内容
                buffer.flip(); // Flip Buffer 将标志位置设为0

                while(buffer.hasRemaining()){
                    byte b = buffer.get();
                    log.debug("读到的字节 {}", (char)b);
                }
                buffer.clear(); // clear the buffer

            }
        }catch (IOException e){

        }

    }
}
