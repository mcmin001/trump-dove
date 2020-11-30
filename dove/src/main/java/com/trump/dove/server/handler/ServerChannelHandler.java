package com.trump.dove.server.handler;

import com.trump.dove.server.ClientReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ServerChannelHandler {
    private static final Logger logger = LoggerFactory.getLogger(ServerChannelHandler.class);

    /**
     * 服务端的selector
     */
    private Selector selector;

    public ServerChannelHandler selector(Selector selector){
        this.selector = selector;
        return this;
    }

    public void start(){
        new ClientReceiver(selector).listen();
    }

    private void printSelectionKeyInfo(String msg, SelectionKey key){
        if(null == key){
            return;
        }
        SocketChannel sc = (SocketChannel) key.channel();
        try {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) sc.getRemoteAddress();
            logger.info("{} . SelectionKey hashcode={} come from {}", msg, key.hashCode(), inetSocketAddress.getAddress().getHostAddress());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
