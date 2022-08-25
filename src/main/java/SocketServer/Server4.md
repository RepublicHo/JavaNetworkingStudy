# More Notes

Java isn't a bad langauge for full-featured web
servers meant to compete with the likes of 
Apache or IIS. Even if you believe CPU-intensive
Java programs are slower than CPU-intensive C/C++ program
most HTTP servers are limited by network bandwidth
and latency, not by CPU speed. 

Consequently, Java's other advantages, such as 
half-compiled/half-interpreted nature, dynamic class
loading, garbage collection, and memory protection
really get a chance to shine. 

In particular, sites that make heavy use of dynamic content
through servlets can often run much faster when reimplemeted 
on top of a pure/mostly pure Java Web Server. I refer interested
readers to Jason Hunter's Java Servlet Programming. However, 
it's important to note that servers in general and web servers
in particular are one area where Java is really competitive with C
for real-world performance. 