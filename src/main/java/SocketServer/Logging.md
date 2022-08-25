# Logging

Servers run unattended for long periods of time. It's often
important to debug what happened. For this reason, 
it's advisable to store server logs for at least some period 
of time. There are two primary things you want to store in
your logs:

1. Requests
2. Server errors

Servers often keep different logfiles for these two different
items. The audit log usually contains one entry for each connection
made to the server. The error log contains mostly unexpected exceptions that
occurred while the server was running. The error log does not contain
client error, such as a client that unexpectedly disconnects or
sends a malformed request.

Many legacy program dating back to Java 1.3 or earlier still 
use third-party logging libraries like log4j and Apache Commons 
Logging, but the java.util.logging package available 