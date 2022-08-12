# Nonblocking I/O

Compared to CPUs and memory and even disks, networks are slow.  
The traditional JAVA solution for allowing CPU to race
ahead of the network is a combination of buffering and 
multithreading. **Multiple threads can generate data for 
several different connections at once and store that data
in buffers until the network is ready to send it.** It works
well for fairly simple servers and clients without extreme performance
needs. 

The overhead of spawning multiple threads and switching 
between them can be nontrivial. Most modern OS we would 
like to be using as a high-volume server supports such
nonblocking I/O. 

But client and even p2p systems rarely need to process so many
simultaneous connections that multithreaded, stream-based I/O 
becomes a noticeable bottleneck. 