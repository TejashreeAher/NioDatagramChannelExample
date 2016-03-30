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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tejashree
 * Fiddle and check that only one thread receives the message at a time, all other threads are blocked.
 */
public class MultiThreadDatagramReceiver {
    private static ExecutorService executor  =  Executors.newFixedThreadPool(3);
    private static DatagramChannel channel;
    
    private static class ReceiverThread implements Runnable{

        @Override
        public void run() {
            System.out.println("IN therad : "+ Thread.currentThread().getId());
            try {
                ByteBuffer buf = ByteBuffer.allocate(48);
                buf.clear();
                channel.receive(buf);
                String msg  = buf.toString();
                System.out.println("Received: " + msg +" by thread : "+ Thread.currentThread().getId());
            } catch (IOException ex) {
                Logger.getLogger(MultiThreadDatagramReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Exiting  : "+ Thread.currentThread().getId());
        }
    
    }
    
    public static void main(String[] args){
        try {
            channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(9999));
            for(int i=0; i<3; i++){
                ReceiverThread t = new ReceiverThread();
                executor.execute(t);
            }
        executor.shutdown();
        } catch (IOException ex) {
            Logger.getLogger(MultiThreadDatagramReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
