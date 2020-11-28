package com.trump.dove.server.handler;

import com.trump.dove.base.DoveBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerChannelHandler {
    private static final Logger logger = LoggerFactory.getLogger(ServerChannelHandler.class);

    private Selector selector;

    public ServerChannelHandler selector(Selector selector){
        this.selector = selector;
        return this;
    }

    public void start(){
        while (DoveBase.alive){
            try {
                int readyChannels = selector.select();
                if(0 == readyChannels){
                    continue;
                }

                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (DoveBase.alive && iterator.hasNext()){
                    SelectionKey key = iterator.next();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
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
