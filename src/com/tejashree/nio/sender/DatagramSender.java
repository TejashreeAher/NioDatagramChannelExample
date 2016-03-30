/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tejashree.nio.sender;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 *
 * @author tejashree
 */
public class DatagramSender {
    //these properties can be changed as needed
    private static final int PORT = 8888;
    private static final String HOST = "localhost";
    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            String newData = "New message :"+ System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            channel.send(buf, new InetSocketAddress(HOST, PORT));
        } catch (IOException e) {

        }
    }
}
