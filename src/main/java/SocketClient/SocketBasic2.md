# Half-closed sockets

Many protocols, such as finger, whois, and HTTP, begin with
the cient sending a request to the server, then reading the response. 
It would be possible to shut down the output after the client
has sent the request. For example, this code fragment sends a 
request to an HTTP server and then shuts down the output, because
it won't need to write anything else over this socket. 

Note that **we still have to close the socket, even if we shut down half or
both halves of a connection.**

## TCP settings

Some Socket options specify how the native sockets
send and receive data. 

1. TCP_NODELAY
2. SO_BINDADDR
3. SO_TIMEOUT
4. SO_LINGER
5. SO_SNDBUG
6. SO_RCVBUF
7. SO_KEEPALIVE
8. OOBINLINE
9. IP_TOS

These funny-looking names are taken from constants
in the C header files. Thus, they follow classic
Unix C naming conventions. For instance, SO_SNDBUF
means "Socket Option Send Buffer Size". 
