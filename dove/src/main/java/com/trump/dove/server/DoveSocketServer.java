package com.trump.dove.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class DoveSocketServer implements Closeable {
    private static final Logger logger = LoggerFactory.getLogger(DoveSocketServer.class);

    private volatile boolean alive;
    private String ip;
    private int port;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public DoveSocketServer(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void start(){
        try{
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(ip, port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            alive = true;
            logger.info("Dove Socket Server has been started success !");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    public void close() throws IOException {
        alive = false;

        if(null != selector){
            selector.close();
        }

        if(null != serverSocketChannel){
            serverSocketChannel.close();
        }

        logger.info("Dove Socket Server has been closed !");
    }

}
