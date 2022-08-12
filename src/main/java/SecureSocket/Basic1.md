# Secure Communication

Most encryption schemes are based on the notion of
a key, a slightly more general kind of password 
that's not limited to text. 

The theory and practice of encryption and authentication, 
both algorithms and protocols, is a challenging field that's
fraught with mines and pitfalls to surprise the amateur 
cryptographer 密码学家.

JSSE shields us from the low-level details of how
algorithms are negotiated, keys are exchanged, correspondents
are authenticated, and data is encrypted. JSSE allows us
to create sockets and server sockets that transparently
handle the negotiations and encryption necessary for secure 
communication. The Java Secure Socket Extension
is divided into four packets. 

javax.net.ssl
javax.net
java.security.cert
com.sun.net.ssl