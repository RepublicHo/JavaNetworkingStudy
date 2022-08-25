# Class of Service

As we all know, different types of Internet services have
different performance needs. For instance, live-streaming
video of sports needs relatively high bandwidth. On the other
hand, a movie might still need bandwidth but be able to
tolerate more delay and latency. Email can be passed
over low-bandwidth connections and even held for several
hours without major harm. 

setPerformancePreference(connectionTime, latency, bandwidth)
for instance, by setting (2, 1, 3), we indicate that
maximum bandwidth is the most important characteristic, 
minimum latency is the least important, and 
connection time is in the middle. 