# Server

A server socket runs on the server abd listens for incoming
TCP connections. Each server socket listens to a particular 
port on the server machine. Server socket for connection while
client socket initiate connections. 

Java provides a ServerSocket class that represents server sockets. 
This class contains everything needed to write servers in Java. 

We won't always have to close the connection after just one write. 
Many protocols, like HTTP 1.1 for instance, allow clients to send
multiple requests over a single socket and expect the server to send 
multiple responses. 
Some protocols such as FTP can even hold a socket open indefinitely. 
