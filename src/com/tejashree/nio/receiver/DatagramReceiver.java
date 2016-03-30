/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tejashree.nio.receiver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tejashree
 */
public class DatagramReceiver {
    public static void main(String[] args){
        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(9999));
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            channel.receive(buf);
            String msg  = buf.toString();
            System.out.println("Received: " + msg);
        } catch (IOException ex) {
            Logger.getLogger(MultiThreadDatagramReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
