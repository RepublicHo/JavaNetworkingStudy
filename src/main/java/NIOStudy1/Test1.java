package NIOStudy1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Anthony Z.
 * @Date 25/8/2022
 * @Description: https://mp.weixin.qq.com/s?__biz=MzkxODI2MDMzMA==&mid=2247485777&idx=1&sn=6fcb5af0a7028d7a88237558d4f5dac9&chksm=c1b55654f6c2df42c648b0d8e177c97f33e155ff13f910515fd026161767c012ed76e1627548&token=1601711&lang=zh_CN#rd
 */
public class Test1 {

    public static void main(String[] args) throws IOException {
        // 新的连接池
        List<SocketChannel> socketChannelList = new ArrayList<>(8);

        // 开启服务端socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(5555));

        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);


    }
}
