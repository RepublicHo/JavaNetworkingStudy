## HTTP Method

Communication with an HTTP server follows a request-response. Each HTTP
request has two or three parts. 

1. A start line containing the HTTP method and a path to the resource on which the method should be executed. 
2. d
3. A request body containing a representation of a resource. 

There are for main HTTP methods. GET, POST, PUT and DELETE. 

1. Get

The GET method retrieves a representation of a resource. It's side-effect free. 
In a properly architected system, GET requests can be bookmarked and prefetched without concern. 
For example, one should not allow a file to be deleted merely by
following a link because a browser may GET all links on a page before 
the user asks it to. 

2. PUT

The PUT method uploads a representation of a resource to the server at a known URL. 

3. DELETE

The DELETE method removes a resource from a specific URL. 

4. POST 

It's the most general method. It uploads a representation of a resource to a 
server at a known URL, but it does not specify what the server is 
to do with the newly supplied resource. 

In practice, POST is vastly overused on the Web. Any safe operation that does not
commit the user to anything should use GET rather than POST. Only operations 
that commit the user should use POST. For example, **adding an item to a shopping
cart should send a GET, because this action doesn't commit; the user can still abandon
cart. However, placing the order should send a POST since that action makes
a commitment. This is why browsers ask you if you're sure to go back to a page that uses POST.**

