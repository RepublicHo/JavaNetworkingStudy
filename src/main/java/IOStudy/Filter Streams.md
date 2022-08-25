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
