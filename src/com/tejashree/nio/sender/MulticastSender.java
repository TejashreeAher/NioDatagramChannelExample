/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tejashree.nio.sender;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

/**
 *
 * @author tejashree
 */
public class MulticastSender {
    private static final int PORT = 8888;
    private static final String GROUP_ADDRESS = "225.0.0.100";
    public static void main(String[] args) {
        try{
        NetworkInterface ni = NetworkInterface.getByName("wlan0");
        InetAddress group = InetAddress.getByName(GROUP_ADDRESS);
        DatagramChannel channel = DatagramChannel.open(StandardProtocolFamily.INET)
			    .setOption(StandardSocketOptions.SO_REUSEADDR, true)
			    .bind(new InetSocketAddress(PORT))
			    .setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
			
           channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            
            MembershipKey key = channel.join(group, ni);
            
            String newData = "New message from multicast sender: " + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            channel.send(buf, new InetSocketAddress(group, PORT));
        } catch (IOException e) {
            System.out.println("IOException while sending message : " + e);
        }
    }

}
