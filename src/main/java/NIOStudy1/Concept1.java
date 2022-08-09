package NIOStudy1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author Anthony Z.
 * @Date 25/7/2022
 * @Description: NIO non-blocking IO
 * NIO将以更加高效的方式进行文件的读写操作
 *
 * 阻塞IO：为解决一个线程为一个客户请求而造成的线程数量的剧增
 * 大量的线程会增加服务器的开销。
 * 一般采用线程池来解决这个问题
 *
 * NIO非阻塞IO:
 * 普通的NIO是线程轮询查看一个IO缓冲区
 * 是否就绪，而JAVA得NIO指的是线程轮询去查看一堆IO缓冲区
 * 是否就绪。
 *
 * NIO适用于
 * 1. Channel & Buffer
  */
public class Concept1 {
    public static void main(String[] args) throws IOException {

        // 1, Establish a server
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 2. Bind the port
        ssc.bind(new InetSocketAddress(8080));

        // 3. accept 能够处理多个连接
        while(true){
            SocketChannel sc = ssc.accept();
        }
        

    }
}
