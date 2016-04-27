# NioDatagramChannelExample
Sending and receiving messages using nio channel. Exploring multiple threaded receivers behavior using nio channel

Simple receiver/Sender :
DatagramSender.java sends messages and DatagramReceiver.java receives messages.
MultiThreadReceiver have multiple threads that listen on the datagram channel. This example shows that only one thread can receive the message at a time and rest of the threads are blocked.

Run receivers first (like a normal java file) and then sender to check the behavior.

******(Enabled lolcommit: Every commit will be captured with a picture")

Multicast Sender/Receiver :
MulticastSender.java sends message onto the channel thath joins a group. MulticastReceiver.java joins the same group to receive messages. MulticastReceiver2.java can be run on another host in the network which joins the same group and it can be seen that both the receivers receives messages sent by the sender
