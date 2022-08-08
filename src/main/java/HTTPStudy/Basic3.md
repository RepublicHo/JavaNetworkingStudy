# URLConnections

URLConnection is an abstract class that represents an active connection to
resource specified by a URL. The URLConnection class has to purposes. 

1. it provides more control over the interaction with a server (especially 
HTTP server) than the URL class. A URLConnection can inspect the header sent by
the server and respond accordingly. *It can set the header fields
used in the client request*

    A URLConnection can send data back to a web server with POST, PUT and 
other HTTP request methods. 

2. The URLConnection class is part of Java's protocol handler mechanism, which also 
include the URLStreamHandler class. The idea behind the protocol handlers is:
they separate the details of processing a protocol from processing 
particular data types, providing user interface, and doing the other
work that a monolithic web browser performs. 

