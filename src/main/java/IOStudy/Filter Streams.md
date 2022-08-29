# Filter Stream 过滤器流

InputStream and OutputStream are fairly raw classes. They read 
and write bytes singly or in groups, and that's all. But actually 
much of the text sent over the Web is either 7-bit ASCII, 
8-bit Latin-1, or multibyte UTF-8. Many files transferred by FTP
are stored in the ZIP format. Java provides a number of filter
classes, (**The filters have 2 versions: the filter streams, and the
reader and writers**) you can attach to raw streams to translate the 
raw bytes to and from these and other formats.

The filter streams still work primarily with raw data as bytes: for 
instance, by compressing the data or interpreting it as binary numbers.
The readers and writers handle the special case of text in a variety
of encodings such as UTF-8 and ISO 8859-1. 

Each filter stream has the same read(), close() and available() methods as 
InputStream. Every filter output stream has the same write(), close(), and flush()
methods as OutputStream. However, in most cases, the filter stream adds other
public methods with additional purposes. At other times, they almost 
completely replace the original interface. For example, it's relatively rare
to use the write() method of PrintStream instead of one of its print() and println()
methods. 

## Chaining Filters together

See ChainFilter.java

Intermixing calls to different streams connected to the same
resource may violate several implicit contracts of the filter streams. 
**Most of the time, we should only use the last filter in the chain
to do the actual reading or writing.**

### Buffered Streams

#### BufferedOutputStream
The BufferedOutputStream class stores written data in a buffer (a protected 
byte array called buf) until the buffer is full or stream is flushed. 
Then it writes the data onto the underlying output stream all at once. 

**A single write of many bytes is almost always much faster
than many small writes that add up to the same thing. This is
especially true of network connections because each TCP segment 
or UDP packet carries a finite amount of overhead, generally about
40 bytes' worth.** Most TCP implementations provide some level of
buffering themselves. Buffering network output is generally a 
huge performance win. 

two constructors
1. public BufferedOutputStream(OutputStream out)
2. public BufferedOutputStream(OutputStream out, int bufferSize)

The important difference is that each write places data in the buffer
rather than directly on the underlying output stream. Consequently, 
it's essential to **flush the stream** when we reach a point at which
the data needs to be sent. 

#### BufferedInputStream
The BufferedInputStream also has a protected byte array named buf
that serves as a buffer. When one of the stream's read() methods is
called, it first tries to get the requested data from the buffer. 


Buffering can substantially improve performance. 
The gain is less obvious on networking connections where bottleneck
is often the speed at which the network can deliver data
rather than the speed at whihc the network interface delivers data
to the program or the speed at which the progrma runs 
Nonetheless, buffering input rarely hurts and will become more important
over time as network speeds increase. 


two constructors
1. public BufferedInputStream(InputStream out)
2. public BufferedInputStream(InputStream out, int bufferSize)

The first argument is the underlying stream from which **unbuffered** data
will be read or to which **buffered** will be written

It only overrides methods from InputStream.

### PrintStream

The PrintStream class is the first filter output stream most programmers 
encounter becase System.out is a PrintStream. 

By default, print streams should be explicitly flushed. 

Each print() method converts its argument to a string
in a predictable fashion and writes the string onto
the underlying stream using the default encoding. 

The println() methods do the same thing, but they append
a platform-dependent line separator ti the end of the line 
they write. 
Linefeed(\n) on Unix(including Max OS), a carriage return(\r) on
Mac OS 9, and a carriage return/linefeed pair on Windows. 

**PrintStream is evil and network programmers should avoid it like plague.**

##### Two problems
The first problem is that output from println() is platform dependent. 
Depending on what system runs the code, lines may sometimes be broken
in three ways. This doesn't cause problems when writing to the console, 
but it's a disaster for writing network clients and servers 
that must follow a precise protocol. 

Most network protocols like HTTP specify the lines should 
be terminated with a CR/LF pair. Using println() makes it easy to 
write a program that runs Windows but fails on Unix. 

The second problem is PrintStream doesn't provide any mechanism for 
changing the default encoding. 

The third problem is that PrintStream eats all exceptions. 

### Data Streams

The binary formats used are primarily intended for exchanging data between two different 
Java programs through a network connection, a datafile, a pipe or some other 
immediately. 

*Both Java and most network protocols were designed by Unix programmers,
and consequently, both tend to the formats common to most Unix systems.* 

***
writeBoolean(boolean b)
writeByte(int b)
writeShort(int s)
writeChar(int c)
writeInt(int c)
writeLong(long l)
writeFloat(float f)
writeDouble(double d)
writeChars(String s)
***

a byte is written as one byte, a short as two bytes, an int as 
four bytes, and a long as eight bytes. Booleans are written as a single 
byte, Chars are written as two unsigned bytes. 

## Readers and Writers

Many modern protocols allow a wide variety of localized
encodings, such as Big-5 Chines. Java provides an almost 
complete mirror of the input and output stream class
hierarchy designed for working with characters instead of bytes. 

Wherever input and output streams use bytes, reader and writers use Unicode
characters. 

The most important concrete subclass of Reader and Writer are the InputStreamReader
and OutputStreamWriter classes. 
1. An InputStreamReader contains an underlying
input stream from which it reads raw bytes. It translates these bytes
into Unicode characters according to a specified encoding. 
2. An OutputStreamWriter receives Unicode characters from a running program. 
It then translate those characters into bytes using a specified 
encoding and writes the bytes onto an underlying output stream. 

#### Filter Readers and Writers

The BufferedReader and BufferedWriter classes are the character-based equivalents
of the byte-oriented BufferedInputStream and BufferedOutputWriter. 