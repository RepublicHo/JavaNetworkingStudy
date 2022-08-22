# UDP

The User Datagram Protocol is another transport layer 
protocol for sending data over IP that is very quick, but not
reliable. When you send UDP data, we have no way of knowing 
whether it arrived, much less whether different pieces of data
arrived in the order in which we sent them. 

Clearly, UDP isn't a good match for applications like FTP that require 
reliable transmission of data. **However, there are many kinds of
applications in which raw speed is more important than getting every
bit right.** Say in real-time audio/video. 

Reliability tests can be implemented in the application layer. The
application is responsible for reliability, and UDP doesn't take care 
of it. The UDP is like postal system (TCP like phone system).

1. UDP doesn't have any notion of a unique connection between two hosts. 
One socket sends and receives all data directed to or from a port
without any concern for who the remote host is. UDP doesn't have any concept of
a connection between two hosts, it only knows about individual datagram. **Figuring out
who sent what data is the application's responsibility.**


2. TCP sockets treat a network connection as a stream: you send and receive data
with input and out put stream that you get from the socket. UDP
doesn't support it. we always work with individual datagram packets. All the data
we stuff into a single datagram is sent as a single packet and is either
received or lost as a group. What's more, given two packets, there is no
way of determining which packet was sent first and which was sent second. Instead
of the orderly queue the data that's necessary for a stream, datagrams try to crowd
into the recipient as quickly as possible, like a crowd of people pushing their way onto 
a bus. And occasionally, if the bus is crowded enough, a few packets, like the people,
will be left waiting at the bus stop. 

 

