/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tejashree.nio.receiver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.StandardSocketOptions;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tejashree
 */
///this is for testing purposes. Run on another host and try receiving the message
public class MultiCastReceiver2 {
    private static final String GROUP_ADDRESS = "225.0.0.100";
    private static final int PORT = 8888;
    public static void main(String[] args){
        try {
            NetworkInterface interf = NetworkInterface.getByName("wlan0"); /// "eth0" can be used for wired connection
            InetAddress group = InetAddress.getByName(GROUP_ADDRESS); ///choose any group address which will be used by sender to send messages
            
            DatagramChannel channel = DatagramChannel.open().bind(new InetSocketAddress(PORT))
                    .setOption(StandardSocketOptions.SO_REUSEADDR, true);
            
            MembershipKey key = channel.join(group, interf);
            
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            channel.receive(buf);
            String msg  = buf.toString();
            System.out.println("Received: " + msg);
        } catch (SocketException ex) {
            Logger.getLogger(MultiCastReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MultiCastReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MultiCastReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
