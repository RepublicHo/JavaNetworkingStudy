package SocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author Anthony Z.
 * @Date 22/8/2022
 * @Description:
 *
 * Read from the input stream and write to it using output stream
 * The trick is understanding the protocol, when to read and when
 * to write.
 */
public class Write {
    static int DEFAULT_PORT = 7000;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        System.out.println("Listening for connection" +
                " on port " + port);

        ServerSocketChannel serverChannel;
        Selector selector;

        try{
            serverChannel = ServerSocketChannel.open();

            ServerSocket serverSocket = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            serverSocket.bind(address);
            serverChannel.configureBlocking(false);
            selector = Selector.open();

        }catch (IOException ex){
            System.err.println(ex);
            return;
        }

        while(true){
            try{
                selector.select();
            }catch (IOException ex){
                System.err.println(ex);
                break;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                try{
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);
                        client.configureBlocking(false);
                        SelectionKey clientKey = client.register(
                                selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ
                        );
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        clientKey.attach(buffer);
                    }

                    if(key.isReadable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        client.read(output);
                    }

                    if(key.isWritable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        output.flip();
                        client.write(output);
                        output.compact();
                    }
                }catch (IOException ex){
                    key.cancel();
                    try{
                        key.channel().close();
                    }catch (IOException cex){

                    }

                }
            }

        }
    }
}
