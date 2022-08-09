# Internet Address

Devices connected to the Internet are called **nodes**.

Nodes that are computers are called **host**.

Each node or host is identified by Internet Address or IP Address. Most
current IP addresses are 4-byte-long IPv4 addresses. A small but growing
number of IP addresses are 16-byte-long IPv6 addresses. The address, technically
speaking, is always numeric IP address, never a human-readable hostname. 

Domain Name System(DNS) associates hostnames that humans can remember (google.com) 
with IP addresses that computers can remember. Servers usually have 
at least one host name. Clients often don't have, especially if their
IP address is dynamically assigned at startup. 

Most of the Time, we can use hostnames and let NDS handle the translation
to IP addresses. Hostnames are much more stable than IP addresses. 
Some services have lived at the same hostname for years, but have switched IP
addresses several times. If we can choose between hostname and IPaddress, always 
choose hostname. 

The InetAddress Class is Java's high-level representation of an
IP address, both IPv4 and IPv6. It's also used by most of other networking
classes, including Socket, ServerSocket, URL, DatagramSocket, DatagramPacket and more. 

