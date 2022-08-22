# Some advanced socket options

## TCP_NODELAY

Problem: Normally, small packets are combined into
larger packets before being sent. Before sending another
packet, the local host waits to receive acknowledgement
of the previous packet from the remote system. This is know
as Nagle's problem (纳格算法是以减少数据包发送量来增进TCP/IP网络的性能).
The problem with it is that if the remote system doesn't 
send acknowledgements back to local system fast enough, applications
that depend on the steady transfer of information may slow down. 

This problem is especially problematic for GUI programs such as
1. games
2. network computer applications where the server
needs to track client-side mouse movement in real time. 

On a really slow network, even typing can be too slow because of the constant
buffering. 

**if (!s.getTcpNoDelay()) s.setTcpNoDelay(true); **

某个应用程序不断地提交小单位的资料，且某些常只占1字节大小。因为TCP数据包具
有40字节的标头信息（TCP与IPv4各占20字节），这导致了41字节大小的数据包只有1
字节的可用信息，造成庞大的浪费。这种状况常常发生于Telnet工作阶段－大部分的键盘
操作会产生1字节的资料并马上提交。更糟的是，在慢速的网络连线下，这类的数据包
会大量地在同一时点传输，造成壅塞碰撞。


## SO_RCVBUF and SO_SNDBUF

TC
