package NIOStudy1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;



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
@Slf4j
public class Server1 {
    public static void main(String[] args) throws IOException {

        // 0.bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1, 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 2. 绑定端口
        ssc.bind(new InetSocketAddress(8080));

        // 3. 能够处理多个连接
        List<SocketChannel> channels = new ArrayList<>();
        while(true){
            // 4. accept建立与客户端连接，SocketChannel用来
            // 与客户端之间通信
            log.debug("connecting......");
            SocketChannel sc = ssc.accept();
            log.debug("connected....");
            channels.add(sc);
            // 5. 接收客户端发送的数据
            for(SocketChannel channel :channels){
                log.debug("before read...");
                channel.read(buffer);
                buffer.flip();
                System.out.println(buffer);
                buffer.clear();
                log.debug("after read...");
            }



        }
        

    }
}
