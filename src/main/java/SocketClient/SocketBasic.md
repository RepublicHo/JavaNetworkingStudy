# Socket for Clients

Data is transmitted across the Internet in packets
of finite size called datagrams. Each datagram contains a header
and a payload. 数据报包含一个报头header和数据本身

The header contains 1. the address and port to which the
packet is going, 2. the adddress and port from which the packet
came, 3. a checksum to detect data corruption,4. 
various other housekeeping info used to ensure reliable transmission. 

The payload contains the data itself. 

Because datagrams have a finite length, it's often 
necessary to split the data across multiple packets and reassemble it
at the destination. It's possible that some packets may be lost
and need to be retransmitted, or packets arrive out
of order and need to be reordered.

However, we don't have to do the work by ourselves. 
Sockets allow the programmer to treat network connection
as just another stream onto which bytes can be written and from which 
bytes can be read. Socket shield the programmer from 
low-level details of the network, such as error detection,
packet splitting, packet retransmission and more. 

A socket can perform seven basic operations. 
1. Connect to a remote machine
2. Send data
3. Receive data
4. Close a connection
5. Bind to a port
6. Listen for incoming data
7. Accept connections from remote machines on the bound port.

Clients do 1-4.
Servers fo 1-7. (5-7 for waiting for 
clients to connect to them.)


## Reading from servers
try **telnet time.nist.gov 13** to get the current
time from National Institute for Standards and Technology. 