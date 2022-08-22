package NIOStudy1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author Anthony Z.
 * @Date 10/8/2022
 * @Description: While NIO is not specifically designed for
 * the clients, they do work for them.
 */
public class Client1 {
    public static void main(String[] args){

    try {
        // Begin by invoking the static factory method SocketChannel.open
        SocketAddress rama = new InetSocketAddress("ASdas.", 2);
        SocketChannel client = SocketChannel.open(rama);
        // The channel is opened in blocking mode.
        // If the connection cannot be estblished, an IOException
        // is thrown.


        // If this were a traditional client, we would now ask for
        // the socket's input or output stream.
        // However, it's not.
        // Rather than writing byte arrays, we write ByteBuffer objects.
        ByteBuffer buffer = ByteBuffer.allocate(74);
        // Say the lines of text are 74 ASCII characters long
        // 72 printable characters followed by a CRLF (carriage return
        // and linefeed pair)

        // Pass this ByteBuffer object to the channel's read() method. The channel
        // fills this buffer with the data it reads from the socket.
        int bytesRead = client.read(buffer);
        // It returns the number of bytes it successfully read abd stored in the buffer
        // returns -1 if it's the end of the data
        // it will often read more bytes if more bytes are available.

    }catch (IOException e){
        System.err.println(e);
    }

    }
}
